<template>
    <div>
        <div>
            <Form ref="formData" :model="formData" :label-width="80">
                <Row style="margin-top: 10px;">
                    <Col span="6">
                    <FormItem label="车次" prop="number">
                      <AutoComplete
                        v-model="formData.number"
                        :data="trainNumbers"
                        @on-search="handleSearchTrainNumber"
                        :transfer="true"
                        placeholder="车次"></AutoComplete>
                    </FormItem>
                    </Col>
                    <Col span="6">
                    <FormItem label="始发站" prop="startStation">
                      <AutoComplete
                        v-model="formData.startStation"
                        :data="startStations"
                        @on-search="handleSearchStartStation"
                        :transfer="true"
                        placeholder="始发站"></AutoComplete>
                    </FormItem>
                    </Col>
                    <Col span="6">
                      <FormItem label="终点站" prop="endStation">
                        <AutoComplete
                          v-model="formData.endStation"
                          :data="endStations"
                          @on-search="handleSearchEndStation"
                          :transfer="true"
                          placeholder="终点站"></AutoComplete>
                      </FormItem>
                    </Col>
                    <Col span="6">
                        <Divider type="vertical" />
                        <Button type="primary" @click="add">添加</Button>
                        <Button type="primary" @click="removeBatch" style="margin-left: 8px">删除</Button>
                        <Button type="primary" @click="query" style="margin-left: 8px">查询</Button>
                    </Col>
                </Row>
            </Form>
        </div>

        <div>
            <Table stripe ref="selection" :columns="columns" :data="rows"></Table>
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

export default {
  mixins: [baseList],
  data () {
    return {
      trainNumbers: [], // 车次自动补全数组数据
      startStations: [], // 始发站自动补全数组数据
      endStations: [], // 始发站自动补全数组数据
      formData: {
        number: '',
        startStation: '',
        endStation: ''
      },
      columns: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '车次',
          key: 'number'
        },
        {
          title: '类型',
          key: 'type'
        },
        {
          title: '始发站',
          key: 'startStation'
        },
        {
          title: '终点站',
          key: 'endStation'
        },
        {
          title: '开始时间',
          key: 'startTime'
        },
        {
          title: '结束时间',
          key: 'endTime'
        },
        {
          title: '操作',
          key: 'action',
          width: 300,
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
                    this.carriage(params.row.id, params.row.number)
                  }
                }
              }, '车厢'),
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
                    this.station(params.row.id, params.row.number)
                  }
                }
              }, '经停站'),
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
                    this.timetable(params.row.id, params.row.number)
                  }
                }
              }, '时刻表'),
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
    // 车厢管理
    carriage (id, number) {
      let r = this.$store.state.app.tagNavList.find((item) => {
        return item.name == `list_train_carriage`
      })
      if (!r) {
        this.$router.push({
          name: `list_train_carriage`,
          query: { numberId: id, trainNumber: number }
        })
      } else {
        this.closeTag(r)
        r.query = { numberId: id, trainNumber: number }
        this.$router.push(r)
      }
    },
    // 经停站管理
    station (id, number) {
      let r = this.$store.state.app.tagNavList.find((item) => {
        return item.name == `list_train_station`
      })
      if (!r) {
        this.$router.push({
          name: `list_train_station`,
          query: { numberId: id, trainNumber: number }
        })
      } else {
        this.closeTag(r)
        r.query = { numberId: id, trainNumber: number }
        this.$router.push(r)
      }
    },
    // 时刻表管理
    timetable (id, number) {
      let r = this.$store.state.app.tagNavList.find((item) => {
        return item.name == `list_train_timetable`
      })
      if (!r) {
        this.$router.push({
          name: `list_train_timetable`,
          query: { numberId: id, trainNumber: number }
        })
      } else {
        this.closeTag(r)
        r.query = { numberId: id, trainNumber: number }
        this.$router.push(r)
      }
    },
    handleSearchTrainNumber (value) {
      if (value) {
        instance.post(`/train/number/list`, `number=${this.formData.number}`).then(response => {
          let data = response.data.map((item) => { return item.number })
          this.trainNumbers = data
        }).catch(error => {
          console.log(error)
        })
      } else {
        this.trainNumbers = []
      }
    },
    handleSearchStartStation (value) {
      if (value) {
        instance.post(`/train/number/start-stations`, `startStation=${this.formData.startStation}`).then(response => {
          this.startStations = response.data
        }).catch(error => {
          console.log(error)
        })
      } else {
        this.startStations = []
      }
    },
    handleSearchEndStation (value) {
      if (value) {
        instance.post(`/train/number/end-stations`, `endStation=${this.formData.endStation}`).then(response => {
          this.endStations = response.data
        }).catch(error => {
          console.log(error)
        })
      } else {
        this.endStations = []
      }
    }
  }

}
</script>
