<template>

    <Form ref="form" :model="formData" :rules="ruleValidate" :label-width="80">
        <input type="hidden" v-model="formData.id"/>
        <input type="hidden" v-model="formData.trainDate"/>

        <Row>
            <Col span="12">
              <FormItem label="乘车人" prop="passengerId">
                <select-passenger v-model="formData.passengerId"></select-passenger>
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem label="车次" prop="trainNumber">
                <Input v-model="formData.trainNumber"></Input>
              </FormItem>
            </Col>
        </Row>
        <Row>
            <Col span="12">
                <FormItem label="出发站" prop="startStationName">
                    <Input v-model="formData.startStationName"></Input>
                </FormItem>
            </Col>
            <Col span="12">
              <FormItem label="到达站" prop="endStationName">
                <Input v-model="formData.endStationName"></Input>
              </FormItem>
            </Col>
        </Row>
        <Row>
          <Col span="12">
            <FormItem label="选择座位" prop="seatType">
              <Select v-model="formData.seatType" @on-change="seatTypeChange" >
                <Option v-for="item in seatTypeList" :value="item.type" :key="item.type">{{ item.type }}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="12">
            <FormItem label="价格" prop="money">
              <Input v-model="formData.money"></Input>
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
import selectPassenger from '_c/select/selectPassenger.vue'
import instance from '@/libs/api'

export default {
  components: { selectPassenger },
  mixins: [baseEdit],
  data () {
    return {
      seatTypeList: [],
      formData: {
        id: '',
        trainDate: '',
        passengerId: '',
        trainNumber: '',
        startStationName: '',
        endStationName: '',
        seatType: '',
        money: '',
        desc: ''
      },
      ruleValidate: {
        trainNumber: [
          { required: true, message: '车次不能为空', trigger: 'blur' }
        ],
        seatType: [
          { required: true, message: '选择座位不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    this.formData.trainNumber = this.$route.query.trainNumber
    this.formData.startStationName = this.$route.query.fromStation
    this.formData.endStationName = this.$route.query.toStation
    this.formData.trainDate = this.$route.query.trainDate

    instance.get(`/purchase/order/list-seat-type/${this.$route.query.numberId}`).then(response => {
      this.seatTypeList = response.data
    }).catch(error => {
      console.log(error)
    })
  },
  methods: {
    seatTypeChange (value) {
      this.formData.money = this.seatTypeList.find(item => {
        return item.type == value
      }).price
    }
  }
}
</script>
