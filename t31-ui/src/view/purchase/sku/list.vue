<template>
  <div>
    <div>
      <Form ref="formData" :model="formData" :rules="ruleValidate" :label-width="80">
        <Row style="margin-top: 10px;">

          <Col span="5">
            <FormItem label="日期" prop="trainDate">
              <DatePicker type="date" :value="formData.trainDate" @on-change="setTrainDate" placeholder="日期"
                           style="width: 100%"></DatePicker>
            </FormItem>
          </Col>

          <Col span="5">
            <FormItem label="车次" prop="trainNumber">

              <AutoComplete
                v-model="formData.trainNumber"
                :data="trainNumbers"
                @on-search="handleSearchTrainNumber"
                :transfer="true"
                placeholder="车次"></AutoComplete>

            </FormItem>
          </Col>
          <Col span="5">
            <FormItem label="出发站" prop="fromStation">
              <AutoComplete
                v-model="formData.fromStation"
                :data="fromStations"
                @on-search="handleSearchFromStation"
                :transfer="true"
                placeholder="出发站"></AutoComplete>
            </FormItem>
          </Col>
          <Col span="5">
            <FormItem label="到达站" prop="toStation">
              <AutoComplete
                v-model="formData.toStation"
                :data="toStations"
                @on-search="handleSearchToStation"
                :transfer="true"
                placeholder="到达站"></AutoComplete>
            </FormItem>
          </Col>
          <Col span="4">
            <Divider type="vertical" />
            <Button type="primary" @click="removeBatch" style="margin-left: 8px">删除</Button>
            <Button type="primary" @click="query" style="margin-left: 8px">查询</Button>
          </Col>
        </Row>
      </Form>
    </div>

    <div>
      <Table border stripe ref="selection" :columns="columns" :data="rows"></Table>
    </div>
    <div class="paging">
      <Page :total="total" :page-size="pageSize" show-sizer show-elevator show-total
            @on-change="changePage" @on-page-size-change="changePageSize"></Page>
    </div>
  </div>
</template>
<style scoped>
.paging {
  float: right;
  margin-top: 10px;
}
</style>
<script>
import { baseList } from '@/libs/crud/base-list'
import instance from '@/libs/api'
import Qs from 'qs'

export default {
  mixins: [baseList],
  data () {
    let day3 = new Date()
    day3.setTime(day3.getTime() + 24 * 60 * 60 * 1000)
    let s3 = day3.getFullYear() + '-' + (day3.getMonth() + 1) + '-' + day3.getDate()

    return {
      trainNumbers: [], // 车次自动补全数组数据
      fromStations: [], // 车次自动补全数组数据
      toStations: [], // 车次自动补全数组数据
      formData: {
        trainDate: s3,
        trainNumber: '',
        fromStation: '',
        toStation: ''
      },
      ruleValidate: {
        trainDate: [
          { required: true, message: '日期不能为空', trigger: 'blur' }
        ]
      },
      columns: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '车次',
          key: 'trainNumber'
        },
        {
          title: '出发站',
          key: 'fromStation'
        },
        {
          title: '到达站',
          key: 'toStation'
        },
        {
          title: '发车',
          key: 'startTime'
        },
        {
          title: '到达',
          key: 'arrivalTime'
        },
        {
          title: '余票',
          align: 'center',
          children: [
            {
              title: '硬座',
              key: 'hardSeatStock',
              width: 70
            },
            {
              title: '一等座',
              key: 'firstSeatStock',
              width: 80
            },
            {
              title: '二等座',
              key: 'secondSeatStock',
              width: 80
            },
            {
              title: '特等座',
              key: 'specialSeatStock',
              width: 80
            },
            {
              title: '硬卧',
              key: 'hardSleeperStock',
              width: 70
            },
            {
              title: '软卧',
              key: 'softSleeperStock',
              width: 70
            }
          ]
        },

        {
          title: '操作',
          key: 'action',
          width: 200,
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.trainOrder(params.row.id, params.row.trainNumber, params.row.fromStation, params.row.toStation, params.row.trainDate, params.row.numberId)
                  }
                }
              }, '购买车票'),
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.edit(params.row.id)
                  }
                }
              }, '修改'),
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                on: {
                  click: () => {
                    this.remove(params.row.id, params.index)
                  }
                }
              }, '删除')
            ])
          }
        }
      ]
    }
  },
  methods: {

    // 下单
    trainOrder (id, trainNumber, fromStation, toStation, trainDate, numberId) {
      let r = this.$store.state.app.tagNavList.find((item) => {
        return item.name == `place_purchase_order`
      })
      if (!r) {
        this.$router.push({
          name: `place_purchase_order`,
          query: { trainNumber: trainNumber, fromStation: fromStation, toStation: toStation, trainDate: trainDate, numberId: numberId }
        })
      } else {
        this.closeTag(r)
        r.query = { trainNumber: trainNumber, fromStation: fromStation, toStation: toStation, trainDate: trainDate, numberId: numberId }
        this.$router.push(r)
      }
    },

    // 查询
    query () {
      this.$refs['formData'].validate((valid) => {
        if (valid) {
          instance.post(`/${this.namespace}/${this.entityName}/list-page`, Qs.stringify(this.formData)).then(response => {
            this.rows = response.data.list
            this.total = response.data.total
          }).catch(error => {
            console.log(error)
          })
        } else {
          this.$Message.error('Fail!')
        }
      })
    },

    setTrainDate (v) {
      this.formData.trainDate = v
    },

    handleSearchTrainNumber (value) {
      if (value) {
        instance.post(`/train/number/list`, `number=${this.formData.trainNumber}`).then(response => {
          let data = response.data.map((item) => { return item.number })
          this.trainNumbers = data
        }).catch(error => {
          console.log(error)
        })
      } else {
        this.trainNumbers = []
      }
    },
    handleSearchFromStation (value) {
      if (value) {
        instance.post(`/train/scheduler/from-stations`, `fromStation=${value}`).then(response => {
          this.fromStations = response.data
        }).catch(error => {
          console.log(error)
        })
      } else {
        this.fromStations = []
      }
    },
    handleSearchToStation (value) {
      if (value) {
        instance.post(`/train/scheduler/to-stations`, `toStation=${value}`).then(response => {
          this.toStations = response.data
        }).catch(error => {
          console.log(error)
        })
      } else {
        this.toStations = []
      }
    }
  }

}
</script>
