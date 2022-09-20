<template>

    <Form ref="form" :model="formData" :rules="ruleValidate" :label-width="80" style="padding: 20px">
        <input type="hidden" v-model="formData.id"/>

        <Row>
            <Col span="12">
                <FormItem label="登录名称" prop="userName">
                    <Input v-model="formData.userName"></Input>
                </FormItem>
            </Col>
            <Col span="12">
                <FormItem label="真实姓名" prop="realName">
                    <Input v-model="formData.realName"></Input>
                </FormItem>
            </Col>
        </Row>
        <Row>
            <Col span="12">
                <FormItem label="密码" prop="password">
                    <Input type="password" v-model="formData.password"></Input>
                </FormItem>
            </Col>
            <Col span="12">
                <FormItem label="确认密码" prop="password2">
                    <Input type="password" v-model="formData.password2"></Input>
                </FormItem>
            </Col>
        </Row>
        <Row>
            <Col span="12">
                <FormItem label="生日" prop="birthday">
                    <DatePicker type="date" :value="formData.birthday" @on-change="setBirthday" placeholder="生日"
                                format="yyyy-MM-dd" style="width: 100%"></DatePicker>
                </FormItem>
            </Col>
            <Col span="12">
                <FormItem label="性别" prop="sex">
                    <RadioGroup v-model="formData.sex">
                        <Radio label="男">男</Radio>
                        <Radio label="女">女</Radio>
                    </RadioGroup>
                </FormItem>
            </Col>
        </Row>
        <Row>
            <Col span="12">
                <FormItem label="电话" prop="tel">
                    <Input v-model="formData.tel"></Input>
                </FormItem>
            </Col>
            <Col span="12">
                <FormItem label="邮箱" prop="email">
                    <Input v-model="formData.email"></Input>
                </FormItem>
            </Col>
        </Row>

        <FormItem label="备注" prop="desc">
            <Input type="textarea" :rows="10" v-model="formData.desc"></Input>
        </FormItem>

        <FormItem>
            <Button type="primary" @click="handleSubmit('form')">保存</Button>
            <Button type="primary" @click="go2Home()" style="margin-left: 8px">关闭</Button>
        </FormItem>

    </Form>

</template>

<script>
import instance from '@/libs/api/index'
import Qs from 'qs'
import { baseEdit } from '@/libs/crud/base-edit'
import { validateTel } from '@/libs/validate/base-rule'

export default {
  data () {
    const validateUserNameRepeat = (rule, value, callback) => {
      instance.post(`/admin/user/validate-name/${value}`, Qs.stringify({ id: this.formData.id })).then(response => {
        if (!response.data.success) {
          callback(new Error('用户名重复'))
        } else {
          callback()
        }
      }).catch(error => {
        console.log(error)
      })
    }

    const validatePassCheck = (rule, value, callback) => {
      if (value !== this.formData.password) {
        callback(new Error('两次密码不一致'))
      } else {
        callback()
      }
    }
    return {
      roleList: [],
      formData: {
        id: '',
        userName: '',
        realName: '',
        password: '',
        password2: '',
        birthday: '',
        sex: '',
        deptId: null,
        deptName: '',
        tel: '',
        email: '',
        postId: '',
        principal: false,
        desc: '',
        roleIds: []
      },
      ruleValidate: {
        userName: [
          { required: true, message: '请输入登录名称', trigger: 'blur' },
          { validator: validateUserNameRepeat, trigger: 'blur' }
        ],
        realName: [
          { required: true, message: '请输入真实姓名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        password2: [
          { required: true, message: '确认密码不能为空', trigger: 'blur' },
          { validator: validatePassCheck, trigger: 'blur' }
        ],
        tel: [
          { validator: validateTel, trigger: 'blur' }
        ],
        email: [
          { type: 'email', message: '请输入正确邮箱', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    setBirthday (v) {
      this.formData.birthday = v
    },
    // 提交
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          instance.post(`/admin/user/register`, Qs.stringify(this.formData, { arrayFormat: 'repeat' })).then(response => {
            this.$Message.success(response.data.msg)
            this.go2Login()
          })
        } else {
          this.$Message.error('Fail!')
        }
      })
    },
    go2Home () {
      this.$router.push({ name: `home` })
    }
  },

  created () {
    instance.get(`/admin/user/self`).then(response => {
      this.formData = Object.assign(response.data)
    }).catch(error => {
      console.log(error)
    })
  }

}
</script>
