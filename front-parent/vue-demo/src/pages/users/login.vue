<template>
  <div>
    <h1>{{welcomeMsg}}</h1>
    <el-form :model="usersForm" ref="usersForm" :rules="usersFormRule">
      <el-form-item prop="username">
        <el-input class="usersFormWidth"
                  placeholder="用户名"
                  v-model="usersForm.username">
          <i slot="prefix"><img src="../../assets/img/icon-user.png"/></i>
          <i scope="error"></i>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input class="usersFormWidth"
                  placeholder="密码"
                  type="password"
                  v-model="usersForm.password">
          <i slot="prefix"><img src="../../assets/img/icon-password.png"/></i>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="success" icon="el-icon-check" circle @click="login('usersForm')"></el-button>
        <el-button type="danger" icon="el-icon-delete" circle @click="resetForm('usersForm')"></el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>

  import baseAxios from '../../common/baseRequest'
  import config from '../../config'

  export default {
    data() {
      let validateUsername = (rule, value, callback) => {
        if (value.trim().length < 5) {
          return callback(new Error('用户名必须大于5位'));
        }
        if (!/^[0-9a-zA-Z]+$/.test(value)) {
          return callback(new Error('用户名须是字母或数字'));
        }
        callback();
      };
      return {
        welcomeMsg: 'Welcome to casir management system,please login in',
        usersForm: {
          username: '',
          password: '',
        },
        usersFormRule: {
          username: [
            {validator: validateUsername, trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'}
          ]
        },
      }
    },
    methods: {
      login(formName) {
        this.$refs[formName].validate((valid) => {
          if (!valid) {
            return false;
          }
          baseAxios.post(config.LOGIN_URL, this.usersForm, {contentType: 'form'}).then(resJson => {
            if (!resJson.success) {
              this.$message({
                showClose: true,
                message: resJson.errorMsg,
                type: 'warning'
              });
              return;
            }
            debugger
            localStorage.setItem("username", resJson.data.username)
            let authorities = resJson.data.authorities.map((value, index, array) => {
              return value.authority
            });
            localStorage.setItem("authorities", JSON.stringify(authorities))
            window.location = `${config.BASE_PATH}/#/mainIndex`;
          })
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    },
    mounted() {

    },
  }
</script>
<style scoped>
  div {
    text-align: center;
  }

  .usersFormWidth {
    width: 300px;
  }
</style>
