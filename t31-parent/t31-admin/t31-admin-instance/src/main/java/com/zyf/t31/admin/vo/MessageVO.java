package com.zyf.t31.admin.vo;

import com.zyf.t31.admin.enums.MessagePlateFormEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageVO {
    private String userName;
    private String mobile;
    private String email;
    private String messageTitle;
    private String messageContent;

    private MessagePlateFormEnum[] plateForm;
}
