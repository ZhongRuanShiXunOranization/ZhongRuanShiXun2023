<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item >个人中心</el-breadcrumb-item>
<!--      :to="{ path: '/' }"-->
      <el-breadcrumb-item>历史记录</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="flex">
      <div class="input_box">
        <el-input
            v-model="keyWord"
            placeholder="搜索历史记录"
            class="input-with-select"
        >
          <template #append>
            <el-button @click="searchList" icon="el-icon-search" circle type="primary"></el-button>
          </template>
        </el-input>
      </div>
    </div>
    <el-table
        class="table_css"
        ref="multipleTable"
        :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        stripe
        :default-sort = "{prop: 'date', order: 'descending'}">
      <el-table-column
          type="selection"
          width="55"

          >
      </el-table-column>
      <el-table-column


          label="日期"
          width="200"
          prop="date"
          sortable
          align="center"
      >
        <template slot-scope="scope">{{ scope.row.date }}</template>
      </el-table-column>
      <el-table-column
          prop="pic_id"
          label="图片ID"
          align="center"
          width="100"
          >
      </el-table-column>
      <el-table-column
          prop="pic_name"
          label="图片"
          width="100"
          align="center"
      >
        <template v-slot="{ row }">
          <el-image
              style="width: 80px; height: 80px"
              :src="img_root + row.pic_name"
              :fit="fit"></el-image>
        </template>

      </el-table-column>
      <el-table-column
          prop="pic_sex"
          label="性别"
          width="100"
          align="center"
          show-overflow-tooltip
          >
      </el-table-column>
      <el-table-column
          prop="pic_age"
          label="年龄"
          width="100"
          align="center">
      </el-table-column>
      <el-table-column
          prop="pic_color"
          label="肤色"
          width="100"
          align="center">
      </el-table-column>
      <el-table-column
          prop="pic_emo"
          label="表情"
          width="100"
          align="center">
      </el-table-column>
      <el-table-column
          prop="pic_glass"
          label="是否戴眼镜"
          width="100"
          align="center">
      </el-table-column>
      <el-table-column
          prop="pic_score"
          label="颜值"
          width="100"
          align="center">
      </el-table-column>

      <el-table-column
          fixed="right"
          label="操作"
          width="120">

        <template slot-scope="scope">
<!--            <el-button type="text" size="small"  @click.native.prevent="handleEdit(scope.$index, scope.row)">查看详情</el-button>-->
            <el-button @click.native.prevent="deleteRow(scope.$index, tableData)" type="text" size="small">删除记录</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin-top: 10px;margin-left: auto" >
      <el-button size="small" @click="toggleSelection()">取消选择</el-button>
      <el-button size="small" @click="delectList()">删除所选记录</el-button>

    </div>
    <div >
      <el-pagination
          style="margin-top: 10px;"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          background
          layout="total,prev, pager, next,jumper"
          :total="tableData.length"
          :page-size="pagesize"
          :current-page="currentPage"
      >
      </el-pagination>
    </div>

  </div>
</template>

<script>
export default {
  name: "historyPage",
  data(){
    return{
      keyWord:"",
      // tableData: [
      //   {
      //     id:"",
      //     date:"2022-03-03 15:13:07",
      //     pic_id:10086,
      //     pic_name:"",
      //     pic_sex:'无',
      //     pic_age:12,
      //     pic_color:"白",
      //     pic_emo:"smell",
      //     pic_glass:"是",
      //     pic_score:9.8
      //   },
      //   {
      //     id:"",
      //     date:"2023-03-03 15:13:07",
      //     pic_id:100186,
      //     pic_sex:'无',
      //     pic_age:12,
      //     pic_color:"白",
      //     pic_emo:"smell",
      //     pic_glass:"是",
      //     pic_score:9.8
      //   },
      //   {
      //     id:"",
      //     date:"2023-03-03 06:13:07",
      //     pic_id:10086,
      //     pic_sex:'无',
      //     pic_age:12,
      //     pic_color:"白",
      //     pic_emo:"smell",
      //     pic_glass:"是",
      //     pic_score:9.8
      //   },
      //   {
      //     id:"",
      //     date:"2023-03-03 15:12:07",
      //     pic_id:10086,
      //     pic_sex:'无',
      //     pic_age:12,
      //     pic_color:"白",
      //     pic_emo:"smell",
      //     pic_glass:"是",
      //     pic_score:9.8
      //   },
      //   {
      //     id:"",
      //     date:"2023-03-03 15:13:07",
      //     pic_id:10086,
      //     pic_sex:'无',
      //     pic_age:12,
      //     pic_color:"白",
      //     pic_emo:"smell",
      //     pic_glass:"是",
      //     pic_score:9.8
      //   },
      //   {
      //     id:"",
      //     date:"2023-03-04 15:13:07",
      //     pic_id:10086,
      //     pic_sex:'无',
      //     pic_age:12,
      //     pic_color:"白",
      //     pic_emo:"smell",
      //     pic_glass:"是",
      //     pic_score:9.8
      //   },
      //   {
      //     id:"",
      //     date:"2023-05-03 15:13:07",
      //     pic_id:10086,
      //     pic_sex:'无',
      //     pic_age:12,
      //     pic_color:"白",
      //     pic_emo:"smell",
      //     pic_glass:"是",
      //     pic_score:9.8
      //   },
      //   {
      //     id: 111,
      //     date: "2023年3月3日星期五"
      //   },
      //   {
      //
      //   },
      //   {
      //
      //   }
      // ],
      img_root : "https://n.sinaimg.cn/sinakd10114/426/w640h586/20200715/",
      tableData: [
        {
          id:"",
          date:"2022-03-03 15:13:07",
          pic_id:10086,
          pic_name:"1537-iwhseiu6789757.jpg",
          pic_sex:'无',
          pic_age:12,
          pic_color:"白",
          pic_emo:"smell",
          pic_glass:"是",
          pic_score:9.8
        },
        {
          id:"",
          date:"2023-03-03 15:13:07",
          pic_id:100186,
          pic_name:"1537-iwhseiu6789757.jpg",
          pic_sex:'无',
          pic_age:12,
          pic_color:"白",
          pic_emo:"smell",
          pic_glass:"是",
          pic_score:9.8
        },
      ],
      currentPage: 1,//初始页
      pagesize: 7,//每页的数据
      detail: []

    }
  },

  methods:{
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },

    formatter(row, column) {
      return row.address;
    },
    deleteRow(index, rows) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        rows.splice(index, 1);
        this.$message({
          duration:'1500',
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          duration:'1500',
          type: 'info',
          message: '已取消删除'
        });
      });

    },

    delectList(){
      this.$confirm('此操作将永久删除所选记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        for (var i = this.$refs.multipleTable.selection.length - 1; i >= 0; i--) {
          this.tableData.splice(this.$refs.multipleTable.selection[i].index - 1, 1)
        }
        this.$message({
          duration:'1500',
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          duration:'1500',
          type: 'info',
          message: '已取消删除'
        });
      });

    },
    searchList(){

    },
    handleSizeChange(val){
      this.pageSize = val;
    },
    //当前页改变时触发 跳转其他页
    handleCurrentChange(val){
      this.currentPage = val;
    },
  }
}
</script>

<style scoped>
.flex{
  margin-top: 20px;
}
.table_css{
  margin-top: 20px;
}
</style>