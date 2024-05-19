<template>
<div>
<HeaderBar />

<table>
    <thead>
        <tr>
            <th>Product Name</th>
            <th>Lot Number</th>
            <th></th>
        </tr>
    </thead> 
    <tbody>
        <tr>
            <td>                    
                <form>
                <select name="insprodnamecat" id="insprodnamecat" v-model="this.prodnamecats.prodnamecat">
                <!-- <option value="">Select Product Name</option>                          -->
                <option v-for="prodname in prodnamecats" v-bind:key="prodname.prodnamecat_cd"> {{ prodname.prodnamecat }}</option>                    
                </select>
                </form>
            </td>
            <td>
                <form>
                <select name="inslotnocat" id="inslotnocat" v-model="this.lotnocats.lotnocat">
                <!-- <option value="">Select Lot Number</option> -->
                <option v-for="lotno in lotnocats" v-bind:key="lotno.lotnocatcat_cd">{{ lotno.lotnocat }}</option>
                </select>
                </form>
            </td>
            <td><button v-on:click="chooseCategories()">Select</button></td>
        </tr>
    </tbody>  
</table>


</div>
</template>



<script>
import axios from 'axios';
//import store from '../store/store';
import HeaderBar from '../components/HeaderBar.vue';
//import FormBar from '../components/FormBar.vue';

export default {
    name: 'PopupCategories',

    components:{
        HeaderBar,
      //  FormBar
        
    },

 data() {
    return {
        prodnamecats: [],
        lotnocats: []
        }     
    },


created(){
    this.username = this.$store.state.SessionUser;
    this.getProdnameCats(),
    this.getLotnoCats()

    },


methods:{
    
    getProdnameCats(){
        axios.get("/api/getProdnames")
        .then((response) => (this.prodnamecats = response.data))
    },

    getLotnoCats(){
        axios.get("/api/getLotnos")
        .then((response) => (this.lotnocats = response.data))
    },

    chooseCategories(){

       // store.commit('setProdname', this.prodnamecats.prodnamecat)
       // store.commit('setLotno', this.lotnocats.lotnocat)

       // this.$emit('selectedProdname', this.prodnamecat);
      //  this.$emit('selectedLotno', this.lotnocat);
//===============================
// const selectedProdname = this.prodnamecats.prodnamecat;
// const selectedLotno = this.lotnocats.lotnocat;

// window.addEventListener('popupClosed', () => {
// window.opener.postMessage('popupClosed', {selectedProdname, selectedLotno});
// });
//============================================
        // window.opener.setProdname(this.prodnamecats.prodnamecat);
        // window.opener.setLotno(this.lotnocats.lotnocat);

        opener.document.getElementById("insprodname").value = this.prodnamecats.prodnamecat;
        opener.document.getElementById("inslotno").value = this.lotnocats.lotnocat;
        // window.opener.postMessage('popupClosed');
        window.close();
       // axios.post("/api/chooseCategories" 
    //     + "&lotno=" + this.result.lotno
    //     + "&qty=" + this.result.qty
    //     + "&proddate=" + this.result.proddate
    //     + "&enddate=" + this.result.enddate
   // )

    //   .then((response) => (response.data = this.$store.state.invItem))
    //         console.log(this.$store.state.invItem)
    //         this.$router.push({name: 'SessionHomeView'})
    //     },

       
      }
    }
}
    
</script>


<style scoped>
.page-container {
width: 100%;
height: 100%;
min-height: 100vh;
position: relative;
}


.form-container {
  max-width: 300px;
  padding: 20px;
  background-color: white;
}

.form-container input[type=text], .form-container input[type=number], .form-container input[type=date]{
  width: 92%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
}

.form-container input[type=text]:focus, .form-container input[type=number]:focus, .form-container input[type=date]:focus {
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

.insertdiv {
  position:absolute;
  z-index: 9999;
  /*border: 3px solid #f1f1f1;*/
  width: 40%;
  margin-top: 5px;
  margin-bottom: 30px;
}

.buttons {
    background-color: #066d06;
    font-size: smaller;
    color: white;
    padding: 5px;
    border: none;
    cursor: pointer;
    margin-left: 5px;
    border-radius: 5%;
    }

</style>
