package com.zyf.t31.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

/**
 * http请求操作类
 *
 * @author Joel
 */
@Slf4j
public class HttpUtil {
	/**
	 * Standard Servlet 2.3+ spec request attributes for include URI and paths.
	 * <p>If included via a RequestDispatcher, the current resource will see the
	 * originating request. Its own URI and paths are exposed as request attributes.
	 */
	public static final String INCLUDE_REQUEST_URI_ATTRIBUTE = "javax.servlet.include.request_uri";
	public static final String INCLUDE_CONTEXT_PATH_ATTRIBUTE = "javax.servlet.include.context_path";

	/**
	 * Default character encoding to use when <code>request.getCharacterEncoding</code>
	 * returns <code>null</code>, according to the Servlet spec.
	 *
	 * @see ServletRequest#getCharacterEncoding
	 */
	public static final String DEFAULT_CHARACTER_ENCODING = "ISO-8859-1";

	public static String obtainAuthorization(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (!StringUtils.hasText(authorization)) {
			return null;
		}

		return authorization;
	}

	public static String getIp(HttpServletRequest request) {
		// 基于 X-Forwarded-For 获得
		String ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个 ip 才是真实 ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		// 基于 X-Real-IP 获得
		ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		// 默认方式
		return request.getRemoteAddr();
	}

	/**
	 * @param request 请求
	 * @return ua
	 */
	public static String getUserAgent(HttpServletRequest request) {
		String ua = request.getHeader("User-Agent");
		return ua != null ? ua : "";
	}

	/**
	 * 根据request拼接queryString
	 *
	 * @return queryString
	 */
	@SuppressWarnings("unchecked")
	public static String buildQueryString(HttpServletRequest request) {
		Enumeration<String> es = request.getParameterNames();
		if (!es.hasMoreElements()) {
			return "";
		}
		String parameterName, parameterValue;
		StringBuilder params = new StringBuilder();
		while (es.hasMoreElements()) {
			parameterName = es.nextElement();
			parameterValue = request.getParameter(parameterName);
			params.append(parameterName).append("=").append(parameterValue).append("&");
		}
		return params.deleteCharAt(params.length() - 1).toString();
	}

	/**
	 * @param request current HTTP request
	 * @return the path within the web application
	 */
	public static String getPathWithinApplication(HttpServletRequest request) {
		String contextPath = getContextPath(request);
		String requestUri = getRequestUri(request);
		if (StringUtils.startsWithIgnoreCase(requestUri, contextPath)) {
			// Normal case: URI contains context path.
			String path = requestUri.substring(contextPath.length());
			return (StringUtils.hasText(path) ? path : "/");
		} else {
			// Special case: rather unusual.
			return requestUri;
		}
	}

	/**
	 * @param request current HTTP request
	 * @return the request URI
	 */
	public static String getRequestUri(HttpServletRequest request) {
		String uri = (String) request.getAttribute(INCLUDE_REQUEST_URI_ATTRIBUTE);
		if (uri == null) {
			uri = request.getRequestURI();
		}
		return normalize(decodeAndCleanUriString(request, uri));
	}

	/**
	 * @param path Relative path to be normalized
	 * @return normalized path
	 */
	public static String normalize(String path) {
		return normalize(path, true);
	}

	/**
	 *
	 * @param path             Relative path to be normalized
	 * @param replaceBackSlash Should '\\' be replaced with '/'
	 * @return normalized path
	 */
	private static String normalize(String path, boolean replaceBackSlash) {

		if (path == null) {
			return null;
		}


		// Create a place for the normalized path
		String normalized = path;

		if (replaceBackSlash && normalized.indexOf('\\') >= 0) {
			normalized = normalized.replace('\\', '/');

		}

		if ("/.".equals(normalized)) {
			return "/";
		}


		// Add a leading "/" if necessary
		if (!normalized.startsWith("/")) {
			normalized = "/" + normalized;
		}


		// Resolve occurrences of "//" in the normalized path
		while (true) {
			int index = normalized.indexOf("//");
			if (index < 0) {
				break;
			}

			normalized = normalized.substring(0, index) +
					normalized.substring(index + 1);
		}

		// Resolve occurrences of "/./" in the normalized path
		while (true) {
			int index = normalized.indexOf("/./");
			if (index < 0) {
				break;
			}

			normalized = normalized.substring(0, index) +
					normalized.substring(index + 2);
		}

		// Resolve occurrences of "/../" in the normalized path
		while (true) {
			int index = normalized.indexOf("/../");
			if (index < 0) {
				break;
			}

			// Trying to go outside our context
			if (index == 0) {
				return (null);
			}

			int index2 = normalized.lastIndexOf('/', index - 1);
			normalized = normalized.substring(0, index2) +
					normalized.substring(index + 3);
		}

		// Return the normalized path that we have completed
		return (normalized);
	}

	/**
	 * Decode the supplied URI string and strips any extraneous portion after a ';'.
	 *
	 * @param request the incoming HttpServletRequest
	 * @param uri     the application's URI string
	 * @return the supplied URI string stripped of any extraneous portion after a ';'.
	 */
	private static String decodeAndCleanUriString(HttpServletRequest request, String uri) {
		uri = decodeRequestString(request, uri);
		int semicolonIndex = uri.indexOf(';');
		return (semicolonIndex != -1 ? uri.substring(0, semicolonIndex) : uri);
	}

	/**
	 * Return the context path for the given request, detecting an include request
	 * URL if called within a RequestDispatcher include.
	 * @param request current HTTP request
	 * @return the context path
	 */
	public static String getContextPath(HttpServletRequest request) {
		String contextPath = (String) request.getAttribute(INCLUDE_CONTEXT_PATH_ATTRIBUTE);
		if (contextPath == null) {
			contextPath = request.getContextPath();
		}
		if ("/".equals(contextPath)) {
			// Invalid case, but happens for includes on Jetty: silently adapt it.
			contextPath = "";
		}
		return decodeRequestString(request, contextPath);
	}

	/**
	 * Decode the given source string with a URLDecoder. The encoding will be taken
	 * from the request, falling back to the default "ISO-8859-1".
	 * <p>The default implementation uses <code>URLDecoder.decode(input, enc)</code>.
	 *
	 * @param request current HTTP request
	 * @param source  the String to decode
	 * @return the decoded String
	 * @see #DEFAULT_CHARACTER_ENCODING
	 * @see ServletRequest#getCharacterEncoding
	 * @see URLDecoder#decode(String, String)
	 * @see URLDecoder#decode(String)
	 */
	@SuppressWarnings({"deprecation"})
	public static String decodeRequestString(HttpServletRequest request, String source) {
		String enc = determineEncoding(request);
		try {
			return URLDecoder.decode(source, enc);
		} catch (UnsupportedEncodingException ex) {
			if (log.isWarnEnabled()) {
				log.warn("Could not decode request string [" + source + "] with encoding '" + enc +
						"': falling back to platform default encoding; exception message: " + ex.getMessage());
			}
			return URLDecoder.decode(source);
		}
	}

	/**
	 * Determine the encoding for the given request.
	 * Can be overridden in subclasses.
	 * <p>The default implementation checks the request's
	 * {@link ServletRequest#getCharacterEncoding() character encoding}, and if that
	 * <code>null</code>, falls back to the {@link #DEFAULT_CHARACTER_ENCODING}.
	 *
	 * @param request current HTTP request
	 * @return the encoding for the request (never <code>null</code>)
	 * @see ServletRequest#getCharacterEncoding()
	 */
	protected static String determineEncoding(HttpServletRequest request) {
		String enc = request.getCharacterEncoding();
		if (enc == null) {
			enc = DEFAULT_CHARACTER_ENCODING;
		}
		return enc;
	}
	/**
	 * 发送GET请求
	 *
	 * @param requestUrl
	 * @return
	 */
	public static Object getRequest(String requestUrl, String charSetName) {
		String res = "";
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
			if (200 == urlCon.getResponseCode()) {
				InputStream is = urlCon.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, charSetName);
				BufferedReader br = new BufferedReader(isr);
				String str = null;
				while ((str = br.readLine()) != null) {
					buffer.append(str);
				}
				br.close();
				isr.close();
				is.close();
				res = buffer.toString();
				return res;
			} else {
				throw new Exception("连接失败");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 发送POST请求
	 *
	 * @param path
	 * @param post
	 * @return
	 */
	public static Object postRequest(String path, String post) {
		URL url = null;
		try {
			url = new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			// 提交模式
			httpURLConnection.setRequestMethod("POST");
			//连接超时 单位毫秒
			httpURLConnection.setConnectTimeout(10000);
			//读取超时 单位毫秒
			httpURLConnection.setReadTimeout(2000);
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
			// 发送请求参数
			//post的参数 xx=xx&yy=yy
			printWriter.write(post);
			// flush输出流的缓冲
			printWriter.flush();
			//开始获取数据
			BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len;
			byte[] arr = new byte[1024];
			while ((len = bis.read(arr)) != -1) {
				bos.write(arr, 0, len);
				bos.flush();
			}
			bos.close();

			return bos.toString("utf-8");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
}
