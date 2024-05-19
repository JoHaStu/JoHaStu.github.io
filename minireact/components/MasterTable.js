import { Button, Card, Table } from "react-bootstrap";
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from "react-router-dom";
//import DetailTable from "./DetailTable";
//import { useNavigate } from "react-router-dom";


const MasterTable = (props) => {
    const {mst_cd, mst_name, mst_notes, reg_date, upd_date} = props.item;
    // const {mst_cd}  = useParams();

    const [entries, setEntries] = useState([]);
    
    let navigate = useNavigate();
    const baseURL = "http://localhost:8080/api/";
    
    const getDetails = async (mst_cd) => {
    const result = await axios.get(baseURL + "dtllist?mst_cd=" + mst_cd);
    setEntries(result.data);
    console.log(entries);
    };

  //   const deleteMst = async (mst_cd) => {
  //     await axios.post(baseURL + "deleteMst?mst_cd=" + mst_cd)
  //     //window.location.reload()
  //     // axios.get(baseURL + "list")
  //      .then((response) => response.data)
  //      .then((response) => {response.data = props.item});
  // }
    // };
    
      let del_mst_cd = mst_cd;
      //let del_dtl_cd = entries.dtl_cd;

      const deleteDtl = async (mst_cd, dtl_cd) => {
        console.log(dtl_cd);
        await axios.post(baseURL + "deleteDtl?mst_cd=" + mst_cd + "&dtl_cd=" + dtl_cd)
        //axios.get(baseURL + "list")
        .then((response) => response.data)
        .then((response) => {setEntries(response)});
    }

   return (
    <div>
    <Card style={{backgroundColor: "darkslategray"}}> 
    <Card.Body>
    <Table striped bordered hover>
    <thead>
         <tr>
           <th></th>
           <th>Code</th>
           <th>Name</th>
           <th>Notes</th>
           <th>Date registered</th>
           <th>Date updated</th>
           <th></th>
           <th></th>
         </tr>
       </thead>
       <tbody>
         <tr>
           <td><Button variant="dark" onClick={() => getDetails(mst_cd)}>Details</Button></td>
           <td>{mst_cd}</td>
           <td>{mst_name}</td>
           <td>{mst_notes}</td>
           <td>{reg_date}</td>
           <td>{upd_date}</td>
           <td><Link to={'/updateForm/' + mst_cd} className="btn btn-dark">Update</Link></td>
           {/* <td><Button variant="dark" onClick={goUpdate(props.item)}>Update</Button></td> */}
           <td><Button variant="dark" onClick={() => props.deleteMst(del_mst_cd)}>Delete</Button></td>
         </tr>
       </tbody>
       </Table>
       {/* <Table striped bordered hover style={{fontsize: 'smaller', fontWeight: 'normal'}}> */}
       <Table striped bordered hover>
        {/* <thead id="dtlheader" style={{visibility: 'hidden'}}> */}
        <thead >
        <tr>
        <th colSpan="8" style={{backgroundColor: "#212529", color: "white"}}>Details</th>
        </tr>
        <tr>
            <th>Master Code</th>
            <th>Detail Code</th>
            <th>Detail Name</th>
            <th>Detail Notes</th>
            <th>Date registered</th>
            <th>Date updated</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {entries.map((entry) => (<tr key={entry.mst_cd} entry={entry}>
          {/* <tr > */}
            <td>{entry.mst_cd}</td>
            <td>{entry.dtl_cd}</td>
            <td>{entry.dtl_name}</td>
            <td>{entry.dtl_notes}</td>
            <td>{entry.reg_date}</td>
            <td>{entry.upd_date}</td>
            <td><Link to={'/dtlUpdateForm/' + mst_cd + '/' + entry.dtl_cd} className="btn btn-dark">Update</Link></td>
           <td><Button variant="dark" onClick={() => deleteDtl(entry.mst_cd, entry.dtl_cd)}>Delete</Button></td>
          </tr>
          ))}
        </tbody>
</Table>
 {/* ))} */}
</Card.Body>
<Link to={'/dtlInsertForm/' + mst_cd} className="btn btn-dark">Add Detail</Link> 
</Card>
<br/>
</div>

  




    );
};

export default MasterTable;