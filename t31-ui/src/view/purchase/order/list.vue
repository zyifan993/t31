<template>
  <div>
    <div>
      <Form ref="formData" :model="formData" :label-width="80">
        <Row style="margin-top: 10px;">

          <Col span="5">
            <FormItem label="日期" prop="trainDate">
              <DatePicker type="date" v-model="formData.trainDate" placeholder="日期"
                          format="yyyy-MM-dd" style="width: 100%"></DatePicker>
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
            <FormItem label="出发站" prop="startStationName">
              <AutoComplete
                v-model="formData.startStationName"
                :data="fromStations"
                @on-search="handleSearchFromStation"
                :transfer="true"
                placeholder="出发站"></AutoComplete>
            </FormItem>
          </Col>
          <Col span="5">
            <FormItem label="到达站" prop="endStationName">
              <AutoComplete
                v-model="formData.endStationName"
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
        // 初始化信息总条数
        total: 0,
        // 每页显示多少条
        pageSize: 10,
        // 显示的数据
        rows: [],

        trainNumbers: [], // 车次自动补全数组数据
        fromStations: [], // 车次自动补全数组数据
        toStations: [], // 车次自动补全数组数据
        formData: {
          trainDate: '',
          trainNumber: '',
          startStationName: '',
          endStationName: ''
        },
        columns: [
          {
            type: 'selection',
            width: 60,
            align: 'center'
          },
          {
            title: '日期',
            key: 'trainDate'
          },
          {
            title: '车次',
            key: 'trainNumber'
          },
          {
            title: '乘车人',
            key: 'passengerName'
          },
          {
            title: '出发站',
            key: 'startStationName'
          },
          {
            title: '到达站',
            key: 'endStationName'
          },
          {
            title: '发车时间',
            key: 'startTime'
          },
          {
            title: '金额',
            key: 'money'
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
                      this.edit(params.row.id)
                    }
                  }
                }, '查看'),
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
      ...mapMutations([
        'closeTag',
        'addTag'
      ]),
      // 添加
      add () {
        let r = this.$store.state.app.tagNavList.find((item) => {
          return item.name == `edit_purchase_${this.entityName}`
        })
        if (!r) {
          this.$router.push({
            name: `edit_purchase_${this.entityName}`
            // query: { id: id }
          })
        } else {
          this.closeTag(r)
          r.query = { id: '' }
          this.$router.push(r)
        }
      },
      // 删除
      remove (id, index) {
        this.$Modal.confirm({
          title: '确认删除',
          content: '确定要删除吗？',
          onOk: () => {
            instance.get(`/${this.namespace}/${this.entityName}/delete/` + id).then(response => {
              this.$Message.info('删除成功')
              this.query()
            }).catch(error => {
              console.log(error)
            })
          }
        })
      },
      // 批量删除
      removeBatch () {
        if (this.$refs.selection.getSelection().length > 0) {
          this.$Modal.confirm({
            title: '确认删除',
            content: '确定要删除吗？',
            onOk: () => {
              let params = new URLSearchParams()
              this.$refs.selection.getSelection().forEach((o) => {
                params.append('ids', o.id)
              })
              instance.post(`/${this.namespace}/${this.entityName}/delete`, params).then(response => {
                this.$Message.info('删除成功')
                this.query()
              })
            }
          })
        } else {
          this.$Message.info('请选择删除的数据')
        }
      },
      // 修改
      edit (id) {
        let r = this.$store.state.app.tagNavList.find((item) => {
          return item.name == `edit_purchase_${this.entityName}`
        })
        if (!r) {
          this.$router.push({
            name: `edit_purchase_${this.entityName}`,
            query: { id: id }
          })
        } else {
          this.closeTag(r)
          r.query = { id: id }
          this.$router.push(r)
        }
      },
      // 查询
      query () {
        instance.post(`/${this.namespace}/${this.entityName}/list-page`, Qs.stringify(this.formData)).then(response => {
          this.rows = response.data.list
          this.total = response.data.total
        }).catch(error => {
          console.log(error)
        })
      },
      // 分页
      changePage (index) {
        this.formData.page = index
        this.query()
      },
      // 设置每页行数
      changePageSize (size) {
        this.formData.page = 1
        this.formData.rows = size
        this.query()
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
    },

    mounted () {
      this.namespace = 'order'
      this.entityName = 'order'
      this.query()
    }

  }
</script>
