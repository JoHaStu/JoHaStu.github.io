import { Button, Card, Table } from "react-bootstrap";
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from "react-router-dom";
//import DetailTable from "./DetailTable";
//import { useNavigate } from "react-router-dom";


const Comments = (props) => {
    const {boardid, commentsid, comm_content, comreg_id, reg_date} = props.comments;
    // const {mst_cd}  = useParams();

    //const [entries, setEntries] = useState([]);
    
    let navigate = useNavigate();
    const baseURL = "http://localhost:8080/api/";
    
    // const getDetails = async (mst_cd) => {
    // const result = await axios.get(baseURL + "dtllist?mst_cd=" + mst_cd);
    // setEntries(result.data);
    // console.log(entries);
    // };

    
  const deleteComment = () => {

  }

   return (
   <div>
    <Card style={{backgroundColor: "darkslategray"}}> 
              <h4 style={{color: "white"}}>Comments</h4>
               <Card.Body>
                <Table>
                    <tbody>
                    <tr>
                        <td colspan="5">{comm_content}</td>
                    </tr>	 
                    <tr>
                        <td>#{commentsid}</td>
                        <td>Posted by {comreg_id}</td>
                        <td>Posted {reg_date}</td>
                        <td><Link to={'/commetnUpdate/' + boardid} className="btn btn-dark">Update</Link></td>
                        <td><Button variant="dark" onClick={() => deleteComment()}>Delete</Button></td>
                    </tr>
                    </tbody>	   
                </Table>
               </Card.Body>
              </Card>
              </div>
    );
};

export default Comments;