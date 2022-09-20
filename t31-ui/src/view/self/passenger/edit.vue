
<template>

    <Form ref="form" :model="formData" :rules="ruleValidate" :label-width="80">
        <input type="hidden" v-model="formData.id"/>

        <FormItem label="姓名" prop="name">
          <Input v-model="formData.name"></Input>
        </FormItem>
        <FormItem label="电话" prop="phone">
            <Input v-model="formData.phone"></Input>
        </FormItem>
        <FormItem label="类型" prop="type">
          <Select v-model="formData.type" placeholder="请选择">
            <Option value="成人">成人</Option>
            <Option value="学生">学生</Option>
          </Select>
        </FormItem>
        <FormItem label="身份证号" prop="idNumber">
          <Input v-model="formData.idNumber"></Input>
        </FormItem>
        <FormItem label="住址" prop="address">
            <Input type="textarea" :rows="10" v-model="formData.address" ></Input>
        </FormItem>
        <FormItem>
            <Button type="primary" @click="handleSubmit('form')">保存</Button>
            <Button type="primary" @click="go2list()" style="margin-left: 8px">关闭</Button>
        </FormItem>

    </Form>

</template>

<script>
import { baseEdit } from '@/libs/crud/base-edit'

export default {
  mixins: [baseEdit],
  data () {
    return {
      formData: {
        id: '',
        name: '',
        phone: '',
        idNumber: '',
        type: '',
        address: ''
      },
      ruleValidate: {
        name: [
          { required: true, message: '名称不能为空', trigger: 'blur' }
        ]
      }
    }
  }
}
</script>
