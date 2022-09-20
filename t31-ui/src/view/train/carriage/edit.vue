<template>

    <Form ref="form" :model="formData" :rules="ruleValidate" :label-width="80">
        <input type="hidden" v-model="formData.numberId"/>
        <input type="hidden" v-model="formData.id"/>

        <Row>
            <Col span="12">
                <FormItem label="车厢编号" prop="carriageNo">
                    <Input v-model="formData.carriageNo"></Input>
                </FormItem>
            </Col>
            <Col span="12">
                <FormItem label="类型" prop="type">
                  <Select v-model="formData.type" placeholder="请选择">
                    <Option value="一等座">一等座</Option>
                    <Option value="二等座">二等座</Option>
                    <Option value="特等座">特等座</Option>
                    <Option value="硬卧">硬卧</Option>
                    <Option value="硬坐">硬坐</Option>
                    <Option value="软卧">软卧</Option>
                  </Select>
                </FormItem>
            </Col>
        </Row>
        <Row>
            <Col span="12">
                <FormItem label="座位数" prop="count">
                    <Input v-model="formData.count"></Input>
                </FormItem>
            </Col>
            <Col span="12">
              <FormItem label="价格" prop="price">
                <Input v-model="formData.price"></Input>
              </FormItem>
            </Col>
        </Row>
        <FormItem label="备注" prop="desc">
            <Input type="textarea" :rows="10" v-model="formData.comment"></Input>
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
        numberId: '',
        type: '',
        carriageNo: '',
        count: '',
        price: '',
        comment: ''
      },
      ruleValidate: {
        type: [
          { required: true, message: '类型不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    let numberId = this.$route.query.numberId
    this.formData.numberId = numberId
  }
}
</script>
