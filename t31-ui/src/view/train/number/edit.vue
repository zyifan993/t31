<template>

  <Form ref="form" :model="formData" :rules="ruleValidate" :label-width="80">
    <input type="hidden" v-model="formData.id"/>
    <Row>
      <Col span="12">
        <FormItem label="日期" prop="trainDate">
          <DatePicker type="date" v-model="formData.trainDate" placeholder="日期"
                      format="yyyy-MM-dd" style="width: 100%"></DatePicker>
        </FormItem>
      </Col>
      <Col span="12">
        <FormItem label="余票数" prop="stock">
          <Input v-model="formData.stock"></Input>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="12">
        <FormItem label="车次" prop="trainNumber">
          <Input v-model="formData.trainNumber"></Input>
        </FormItem>
      </Col>
      <Col span="12">
        <FormItem label="运行时长" prop="duration">
          <Input v-model="formData.duration"></Input>
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
          trainNumber: '',
          trainDate: '',
          startStation: '',
          endStation: '',
          fromStation: '',
          toStation: '',
          startTime: '',
          arrivalTime: '',
          duration: ''
        },
        ruleValidate: {
          trainNumber: [
            { required: true, message: '车次不能为空', trigger: 'blur' }
          ]
        }
      }
    }
  }
</script>
