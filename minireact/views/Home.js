import React, { useState, useEffect } from 'react';
import MasterTable from '../components/MasterTable';
import DetailTable from '../components/DetailTable';
import axios from 'axios';
import { useParams } from "react-router-dom";
import { Card } from 'react-bootstrap';
import Header from '../components/Header';


const Home = (props) => {
//const {mst_cd} = useParams();
//const searchMst =() => {props.searchMst}
//const [showDetails, setShowDetails] = useState(false);
const [items, setItems] = useState([]);
//const [entries, setEntries] = useState([]);
//setEntries(props.entry);


const baseURL = "http://localhost:8080/api/";

useEffect(() => {
    setItems(props.searchResult)
}, [props.searchResult])

useEffect(() => {
    axios.get(baseURL + "list")
    .then((response) => response.data)
    .then((response) => {setItems(response)}, console.log(items));
}, []) //empty array added to stop useEffect to keep endlessly rendering

// const getDetails = (async (mst_cd) => {
//     const result = await axios.get(baseURL + "dtllist?mst_cd=" + mst_cd);
//     console.log(result.data);
//     setEntries(result.data);
//     setShowDetails(true);
//  }, [])

//const {mst_cd}  = useParams();
// useEffect(() => {
//     axios.get(baseURL + "dtllist", items.mst_cd)
//     .then((response) => response.data)
//     .then((response) => {setEntries(response)}, console.log(entries));
// }, []) 
const [mst_cd_del, setMst_cd_del] = useState();
//const [dtl_cd_del, setDtl_cd_del] = useState();

const deleteMst = async (mst_cd_del) => {
    setMst_cd_del(mst_cd_del);
    console.log(mst_cd_del);
    await axios.post(baseURL + "deleteMst?mst_cd=" + mst_cd_del)
    //axios.get(baseURL + "list")
    .then((response) => response.data)
    .then((response) => {setItems(response)});
}


// const searchMst = (e) => {
//     e.preventDefault();
//     axios.get(baseURL + "searchMst?searchword=" + props.searchword)
//     .then((response) => response.data)
//     .then((response) => {setItems(response)}, console.log(items));
// }
  


    return (
        <div>
            
            {/* {items.map((item) => (<MasterTable key={item.mst_cd} item={item}/>))} */}
            {items.map((item) => (<MasterTable key={item.mst_cd} item={item} deleteMst={deleteMst}/>))} 
            {/* {items.map((item) => (<MasterTable key={item.mst_cd} item={item} getDetails={getDetails} />))} */}
            {/* {showDetails && entries.map((entry) => (<DetailTable key={entry.dtl_cd} entry={entry} />))} */}
            </div>
    );
};





export default Home;