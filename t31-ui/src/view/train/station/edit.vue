<template>

    <Form ref="form" :model="formData" :rules="ruleValidate" :label-width="80">
        <input type="hidden" v-model="formData.numberId"/>
        <input type="hidden" v-model="formData.id"/>

        <Row>
            <Col span="12">
                <FormItem label="车站编号" prop="num">
                    <Input v-model="formData.num"></Input>
                </FormItem>
            </Col>
            <Col span="12">
              <FormItem label="车站名" prop="name">
                <Input v-model="formData.name"></Input>
              </FormItem>
            </Col>
        </Row>
        <Row>
            <Col span="12">
              <FormItem label="到达类型" prop="arriveType">
                <Select v-model="formData.arriveType" clearable placeholder="请选择">
                  <Option value="当日到达">当日到达</Option>
                  <Option value="次日到达">次日到达</Option>
                  <Option value="第三日到达">第三日到达</Option>
                  <Option value="第四日到达">第四日到达</Option>
                </Select>
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem label="运行时间" prop="duration">
                <Input v-model="formData.duration"></Input>
              </FormItem>
            </Col>
        </Row>
        <Row>
          <Col span="12">
            <FormItem label="到达时间" prop="arriveTime">
              <Input v-model="formData.arriveTime"></Input>
            </FormItem>
          </Col>
          <Col span="12">
            <FormItem label="发车时间" prop="startTime">
              <Input v-model="formData.startTime"></Input>
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
        arriveType: '',
        arriveTime: '',
        startTime: '',
        duration: '',
        num: '',
        name: '',
        comment: ''
      },
      ruleValidate: {
        name: [
          { required: true, message: '名称不能为空', trigger: 'blur' }
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
