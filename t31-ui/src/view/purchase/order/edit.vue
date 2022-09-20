<template>

  <Form ref="form" :model="formData" :rules="ruleValidate" :label-width="80">
    <input type="hidden" v-model="formData.id"/>

    <Row>
      <Col span="12">
        <FormItem label="车次" prop="trainNumber">
          <Input v-model="formData.trainNumber"></Input>
        </FormItem>
      </Col>
      <Col span="12">
        <FormItem label="乘车人" prop="passengerName">
          <Input v-model="formData.passengerName"></Input>
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
        <FormItem label="座位类型" prop="seatType">
          <Input v-model="formData.seatType"></Input>
        </FormItem>
      </Col>
      <Col span="12">
        <FormItem label="金额" prop="money">
          <Input v-model="formData.money"></Input>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="12">
        <FormItem label="乘车日期" prop="trainDate">
          <Input v-model="formData.trainDate"></Input>
        </FormItem>
      </Col>
      <Col span="12">
        <FormItem label="到达时间" prop="startTime">
          <Input v-model="formData.startTime"></Input>
        </FormItem>
      </Col>
    </Row>
    <FormItem label="备注" prop="comment">
      <Input type="textarea" :rows="10" v-model="formData.comment"></Input>
    </FormItem>

    <FormItem>
      <Button type="primary" @click="go2list()" style="margin-left: 8px">关闭</Button>
    </FormItem>

  </Form>

</template>

<script>

  import instance from '@/libs/api/index'
  import Qs from 'qs'
  import { mapMutations } from 'vuex'

  export default {
    data () {
      return {
        // 当前路由的子目录/admin/post/1 -> security
        namespace: '',
        // 当前路由的最后访问路径/admin/post/1-> post
        entityName: '',

        formData: {
          id: '',
          trainDate: '',
          trainNumber: '',
          passengerId: '',
          startStationName: '',
          endStationName: '',
          seatType: '',
          money: '',
          startTime: '',
          comment: ''
        }
      }
    },

    methods: {
      ...mapMutations([
        'closeTag'
      ]),
      /**
       * 模板方法：提交前用来处理保存的数据
       */
      beforeSubmit () {
        alert('b')
      },

      // 提交
      handleSubmit (name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            instance.post(`/${this.namespace}/${this.entityName}/save`, Qs.stringify(this.formData, { arrayFormat: 'repeat' })).then(response => {
              this.$Message.success(response.data.msg)
              this.go2list()
            })
          } else {
            this.$Message.error('Fail!')
          }
        })
      },
      // 重置
      handleReset (name) {
        this.$refs[name].resetFields()
      },
      // 根据ID加载数据
      get (id) {
        instance.get(`/${this.namespace}/${this.entityName}/edit/` + id).then(response => {
          this.formData = Object.assign(response.data)
        }).catch(error => {
          console.log(error)
        })
      },

      go2list () {
        this.closeTag(this.$route)
      }
    },

    mounted () {
      this.namespace = 'order'
      this.entityName = 'order'

      let id = this.$route.query.id
      if (id) {
        this.get(id)
      } else {
        // Object.keys(this.formData).forEach(key => this.formData[key] = '')
      }
    }

  }
</script>
