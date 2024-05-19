<template>
 <div class="page-container">
    <div class="header">
       <h2 style="color:white;">Mini Vue Project</h2>
    </div>

    <div id="UpdProduct" class="updatediv">
	
    <div id="updateFormDiv" style="font-size: smaller;" class="tabcontent">
    <form id="updateForm" method="post" class="form-container" @submit.prevent="updateMst()"> 

     <h2>Update Item <span id="mst_cd" name="mst_cd">{{ $store.state.mstItem.mst_cd }}</span></h2>
 
     <label>Name</label>
     <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Update Name" id="mst_name" name="mst_name" v-model="this.result.mst_name">
     
     <label>Notes</label>
     <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Update Notes" id="mst_notes" name="mst_notes" v-model="this.result.mst_notes">
         
     <button type="submit" class="btn" >Update</button>
   </form>
   </div>
   </div>

   </div>
</template>

<script>
//import { reactive } from 'vue';
import axios from 'axios';
//import store from '@/store/store';
//import router from '@/router/index';

export default {
 data() {
    return {
      result: {
       // mst_cd: '',
        mst_name: '',
        mst_notes: ''
    }
    }
  },
created(){
   console.log(this.$store.state.mstItem)
   this.result = this.$store.state.mstItem
},
methods:{
   updateMst(){
     // const params = { params: { mst_name: this.result.mst_name, mst_notes: this.result.mst_notes } }
    //  console.log("new" + params)
   //  axios.post("/api/updateMst", this.result) THIS LINE
       
       //axios.post("/api/updateMst", this.$store.state.mstItem)THIS LINE
       
        axios.post("/api/updateMst", {
        mst_cd: this.$store.state.mstItem.mst_cd,
        mst_name: this.result.mst_name,
        mst_notes: this.result.mst_notes,
      })

     /*  .then(response => {
        console.log(response)
        this.$router.push({name: 'ListView'})
      })*/
          
         .then((response) => (response.data = this.$store.state.mstItem))
         
      /*.then((res) => {s
          if (res.data.state) {
            this.$store.commit('setMstItem', this.result)
          } else {
            alert(res.data.message)
          }
        })
        .catch((err) => console.log(err))*/
         console.log(this.$store.state.mstItem)
        this.$router.push({name: 'ListView'})
   }
   
  }
   
  }


   /* ===========================
   setup() {
    const state = reactive({
      form: {
        mst_name: "",
        mst_notes: ""
      }
    })

    const submit = () => {
      axios.post("/api/updateMst", state.form).then((res) => {
        store.commit('setsetMstItemAccount', res.data);
        router.push({path: "/list"});
        window.alert("updated");
      }).catch(() => {
        window.alert("failed");
      });
    }

    return {state, submit}
  }
}==========================
*/
   

</script>

<style scoped>
.page-container {
width: 100%;
height: 100%;
min-height: 100vh;
position: relative;
}

.header {
background-color: rgb(126, 2, 2);
padding: 15px;
text-align: center; 
width: 100%;
margin-top: 0px;
}

.form-container {
  max-width: 300px;
  padding: 20px;
  background-color: white;
}

.form-container input[type=text], .form-container input[type=number]{
  width: 92%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
}

.form-container input[type=text]:focus, .form-container input[type=number]:focus {
  background-color: #ddd;
  outline: none;
}

.form-container .btn {
  background-color: #04442f;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom:10px;
  margin-top: 10px;
}

.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}

.tabcontent {
  width: 100%;
  padding: 6px 12px;
  border: 1px solid #ccc;
  box-align: center;
}

.updatediv {
  position:absolute;
  z-index: 9999;
 /* border: 3px solid #f1f1f1;*/
  width: 22%;
  margin-top: 20px;
  margin-bottom: 30px;
}

.error{
  font-size: smaller;
  background-color: #066d06;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 8px;
  margin-left: 8px;
  display: inline-block;
}
</style>