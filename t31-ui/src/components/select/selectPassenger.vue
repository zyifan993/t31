<template>

    <Select :value="currentValue" @on-change="handleInput" >
        <Option v-for="item in passengerList" :value="item.id" :key="item.id">{{ item.name }}</Option>
    </Select>

</template>

<script>
import instance from '@/libs/api/index'
export default {
  name: 'selectPassenger',
  data () {
    return {
      passengerList: []
    }
  },
  computed: {
    currentValue: function () {
      return this.value
    }
  },
  props: ['value'], // 接收一个 value prop
  methods: {
    handleInput (value) {
      this.$emit('input', value) // 触发 input 事件，并传入新值,v-model：使用:value读，使用@input写
    }
  },
  mounted () {
    instance.post(`/self/passenger/list`).then(response => {
      this.passengerList = response.data
    }).catch(error => {
      console.log(error)
    })
  }
}
</script>
