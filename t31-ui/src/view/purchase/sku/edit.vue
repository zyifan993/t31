<template>

    <Form ref="form" :model="formData" :rules="ruleValidate" :label-width="80">
        <input type="hidden" v-model="formData.numberId"/>
        <input type="hidden" v-model="formData.id"/>

        <Row>
            <Col span="12">
                <FormItem label="出发站" prop="fromStation">
                    <Input v-model="formData.fromStation"></Input>
                </FormItem>
            </Col>
            <Col span="12">
              <FormItem label="到达站" prop="toStation">
                <Input v-model="formData.toStation"></Input>
              </FormItem>
            </Col>
        </Row>
        <Row>
            <Col span="12">
                <FormItem label="始发站" prop="startStation">
                    <Input v-model="formData.startStation"></Input>
                </FormItem>
            </Col>
            <Col span="12">
              <FormItem label="终点站" prop="endStation">
                <Input v-model="formData.endStation"></Input>
              </FormItem>
            </Col>
        </Row>
        <Row>
          <Col span="12">
            <FormItem label="发车时间" prop="startTime">
              <Input v-model="formData.startTime"></Input>
            </FormItem>
          </Col>
          <Col span="12">
            <FormItem label="到达时间" prop="arrivalTime">
              <Input v-model="formData.arrivalTime"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="24">
            <FormItem label="运行时间" prop="duration">
              <Input v-model="formData.duration"></Input>
            </FormItem>
          </Col>
        </Row>
        <FormItem label="备注" prop="desc">
            <Input type="textarea" :rows="10" v-model="formData.desc"></Input>
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
        startStation: '',
        endStation: '',
        fromStation: '',
        toStation: '',
        startTime: '',
        arrivalTime: '',
        duration: '',
        desc: ''
      },
      ruleValidate: {
        startStation: [
          { required: true, message: '始发站不能为空', trigger: 'blur' }
        ],
        endStation: [
          { required: true, message: '始发站不能为空', trigger: 'blur' }
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
