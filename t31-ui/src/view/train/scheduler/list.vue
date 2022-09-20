<template>
    <div>
        <div>
            <Form ref="formData" :model="formData" :label-width="80">
                <Row style="margin-top: 10px;">
                    <Col span="6">
                      <FormItem label="车次" prop="trainNumber">

                        <AutoComplete
                          v-model="formData.trainNumber"
                          :data="trainNumbers"
                          @on-search="handleSearchTrainNumber"
                          :transfer="true"
                          placeholder="车次"></AutoComplete>

                      </FormItem>
                    </Col>
                    <Col span="6">
                      <FormItem label="出发站" prop="fromStation">
                        <AutoComplete
                          v-model="formData.fromStation"
                          :data="fromStations"
                          @on-search="handleSearchFromStation"
                          :transfer="true"
                          placeholder="出发站"></AutoComplete>
                      </FormItem>
                    </Col>
                    <Col span="6">
                      <FormItem label="到达站" prop="toStation">
                        <AutoComplete
                          v-model="formData.toStation"
                          :data="toStations"
                          @on-search="handleSearchToStation"
                          :transfer="true"
                          placeholder="到达站"></AutoComplete>
                      </FormItem>
                    </Col>
                    <Col span="6">
                        <Divider type="vertical" />
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
import Qs from 'qs'

export default {
  mixins: [baseList],
  data () {
    return {
      trainNumbers: [], // 车次自动补全数组数据
      fromStations: [], // 车次自动补全数组数据
      toStations: [], // 车次自动补全数组数据
      formData: {
        trainNumber: '',
        fromStation: '',
        toStation: ''
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
          title: '始发站',
          key: 'startStation'
        },
        {
          title: '终点站',
          key: 'endStation'
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
          title: '发车时间',
          key: 'startTime'
        },
        {
          title: '到达时间',
          key: 'arrivalTime'
        }
      ]
    }
  },
  methods: {
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
