import { Button, Card, Table } from "react-bootstrap";
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from "react-router-dom";
import Session from 'react-session-api';


const BoardList = (props) => {
    const {boardid, title, content, reg_id, reg_date} = props.board;
    
    let currentuser = Session.get("currentuser");
    
    let navigate = useNavigate(); 

    const baseURL = "http://localhost:8080/api/";
    
   // const [error, setError] = useState(false);
   
const goUpdateBoard = () => {
  if(currentuser === reg_id){
    navigate ('/updateForm/' + boardid)
  }
  else{
    //setError(true);
    alert("You can't update other people's posts")
  }
}

      let del_boardid = boardid;
      let del_reg_id = reg_id;

  //     const deleteBoard = async (boardid, reg_id) => {
  //       console.log(reg_id);
  //       if(currentuser === reg_id){
  //       await axios.post(baseURL + "deleteBoard?boardid=" + boardid)
  //       //axios.get(baseURL + "list")
  //       .then((response) => response.data)
  //       //.then((response) => {setComments(response)});
  //   }
  //   else{
  //     alert("You can't delete oterh people's posts")
  //   }
  // }

   return (
    <div>
    <Card style={{backgroundColor: "darkslategray"}}> 
    <Card.Body>
    <Table striped bordered hover>
    <thead>
         <tr>
           <th>#</th>
           <th>Title</th>
           <th>Content</th>
           <th>Posted by</th>
           <th>Posted on</th>
           <th></th>
           <th></th>
           <th></th>
         </tr>
       </thead>
       <tbody>
         <tr>
            {/* {all.map((every) => (<tr key={every.mst_cd} every={every} allSearchResult={props.allSearchResult}> */}
           <td>{boardid}</td>
           <td>{title}</td>
           <td>{content}</td>
           <td>{reg_id}</td>
           <td>{reg_date}</td>
           {/* <td><Button variant="warning" onClick={() => getDetails(boardid)}>See more</Button></td> */}
           <td><Link to={'/detailView/' + boardid} className="btn btn-warning">See more</Link></td>
           {/* <td><Link to={'/updateForm/' + boardid} className="btn btn-dark">Update</Link></td> */}
           <td><Button variant="dark" onClick={() => goUpdateBoard(boardid, reg_id)}>Update</Button></td>
           {/* <td><Button variant="dark" onClick={() => deleteBoard(boardid, reg_id)}>Delete</Button></td> */}
           <td><Button variant="dark" onClick={() => props.deleteBoard(del_boardid, del_reg_id)}>Delete</Button></td>
         </tr>
       </tbody>
       </Table>
</Card.Body>
</Card>
<br/>
</div>

  




    );
};

export default BoardList;