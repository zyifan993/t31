<template>
    <div>
        <div>
            <Form ref="formData" :model="formData" :label-width="80">
                <input type="hidden" v-model="formData.numberId"/>
                <Row style="margin-top: 10px;">
                  <Col span="8">
                    <FormItem label="车次" prop="trainNumber">
                      <Input v-model="formData.trainNumber" disabled placeholder="车次"></Input>
                    </FormItem>
                  </Col>
                  <Col span="8">
                    <FormItem label="类型" prop="type">
                      <Select v-model="formData.type" clearable placeholder="请选择">
                        <Option value="一等座">一等座</Option>
                        <Option value="二等座">二等座</Option>
                        <Option value="特等座">特等座</Option>
                        <Option value="硬卧">硬卧</Option>
                        <Option value="硬坐">硬坐</Option>
                        <Option value="软卧">软卧</Option>
                      </Select>
                    </FormItem>
                  </Col>
                    <Col span="8">
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
      formData: {
        numberId: '',
        trainNumber: '',
        type: ''
      },
      columns: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '车厢号',
          key: 'carriageNo'
        },
        {
          title: '类型',
          key: 'type'
        },
        {
          title: '座位数量',
          key: 'count'
        },
        {
          title: '价格',
          key: 'price'
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
                    this.edit(params.row.id, this.formData.numberId)
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
  created () {
    let numberId = this.$route.query.numberId
    this.formData.numberId = numberId
    this.formData.trainNumber = this.$route.query.trainNumber
  },
  methods: {
    // 添加
    add () {
      let r = this.$store.state.app.tagNavList.find((item) => {
        return item.name == `edit_train_carriage`
      })
      if (!r) {
        this.$router.push({
          name: `edit_train_carriage`,
          query: { numberId: this.formData.numberId }
        })
      } else {
        this.closeTag(r)
        r.query = { numberId: this.formData.numberId }
        this.$router.push(r)
      }
    },
    // 修改
    edit (id, numberId) {
      let r = this.$store.state.app.tagNavList.find((item) => {
        return item.name == `edit_train_carriage`
      })
      if (!r) {
        this.$router.push({
          name: `edit_train_carriage`,
          query: { id: id, numberId: numberId }
        })
      } else {
        this.closeTag(r)
        r.query = { id: id, numberId: numberId }
        this.$router.push(r)
      }
    }
  }

}
</script>
