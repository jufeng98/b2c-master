<template>
  <div class="customize-cls">
    <el-table :data="tableData3" style="width: 100%;" max-height="800" stripe border
              :row-class-name="tableRowClassName">
      <el-table-column prop="name" label="姓名" width="120" fixed></el-table-column>
      <el-table-column prop="gender" label="性别" :formatter="formatDict" width="60"></el-table-column>
      <el-table-column prop="province" label="省份" width="120"></el-table-column>
      <el-table-column prop="city" label="市区" width="120"></el-table-column>
      <el-table-column prop="address" label="地址" width="600"></el-table-column>
      <el-table-column prop="zip" label="邮编" width="120"></el-table-column>
      <el-table-column prop="servicePrice" label="服务费" :formatter="formatMoney"
                       width="100"></el-table-column>
      <el-table-column prop="date" label="日期" :formatter="formatDateTime" width="300"></el-table-column>
      <el-table-column prop="video" label="视频" width="200">
        <template slot-scope="scope">
          <video width="200px" height="200px" controls="">
            <source :src="scope.row.video" type="video/mp4">
          </video>
        </template>
      </el-table-column>
      <el-table-column prop="pic" label="图片" width="200">
        <template slot-scope="scope">
          <img :src="scope.row.pic" alt="图片" style="width:100px;height: 100px"/>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button style="float: left" type="primary" @click="goBack">返回前一页</el-button>
  </div>
</template>
<script>

import moment from 'moment'
import {BigNumber} from 'bignumber.js'

export default {
  name: 'HelloWorld',
  data() {
    return {
      GENDER: {
        M: '男',
        F: '女',
      },
      DATE_PATTERN: 'YYYY-MM-DD HH:mm:ss',
      BIG_NUMBER_100: new BigNumber(100),
      tableData3: [],
    }
  },
  methods: {
    handleClick(row) {
      console.log(row);
    },
    formatDateTime(rowData, rowProperty, cellValue, rowIndex) {
      if (!cellValue) {
        return
      }
      return moment(cellValue).format(this.DATE_PATTERN)
    },
    formatMoney(rowData, rowProperty, cellValue, rowIndex) {
      return new BigNumber(cellValue).dividedBy(this.BIG_NUMBER_100).toFixed(2)
    },
    formatDict(rowData, rowProperty, cellValue, rowIndex) {
      return this.GENDER[cellValue]
    },
    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 0) {
        return 'warning-row'
      }
      return ''
    },
    getUserList() {
      let self = this
      this.$baseAxios.post(this.$config.GET_USER_LIST_URL, {
        name: 'liangyudong',
        gender: 'M',
      }).then((resJson) => {
        self.tableData3 = resJson.tableData3
      })
    },
    goBack() {
      window.history.length > 1 ? this.$router.go(-1) : this.$router.push('/')
    }
  },
  mounted() {
    this.getUserList()
    console.log("query:", this.$route.query);
    console.log("params:", this.$route.params);
  },
}
</script>

<style>
.warning-row {
  font-weight: bold;
  font-style: italic;
}
</style>
<style scoped>
.customize-cls >>> .el-table__header {
  font-style: italic;
}
</style>
