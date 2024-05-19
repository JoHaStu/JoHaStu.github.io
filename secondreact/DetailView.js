import React, { useState, useEffect } from 'react';
import Comments from '../components/Comments';
import DetailHeader from '../components/DetailHeader';
import axios from 'axios';
import { Link, useParams, useNavigate } from "react-router-dom";
import { Button, Card, Container, Table } from 'react-bootstrap';
import Session from 'react-session-api';



const DetailView = (props) => {

const {boardid}  = useParams();

let currentuser = Session.get("currentuser");

const [board, setBoard] = useState({
   // boardid: "",
    title: "",
    content: "",
    reg_id: "",
    reg_date: Date,
});

const [comments, setComments] = useState([]
//     {
//     boardid: "",
//     commentsid: "",
//     comm_comment: "",
//     comreg_id: "",
//     reg_date: Date,
// }
);



const baseURL = "http://localhost:8080/api/";

let navigate = useNavigate(); 


useEffect(() => {
    axios.get(baseURL + "detailView?boardid=" + boardid)
    .then((response) => {setBoard(response.data);
    }, console.log(board));
}, []) 

useEffect(() => {
    axios.get(baseURL + "getComments?boardid=" + boardid)
    .then((res) => res.data)
    .then((res) => {setComments(res)
    }, console.log(comments));
}, []) 



const deleteComment = async (boardid, commentsid, comreg_id) => {
    console.log(commentsid);
    console.log(comreg_id);
    console.log(currentuser); 
    if(currentuser === comreg_id){
    await axios.post(baseURL + "deleteComment?boardid=" + boardid + "&commentsid=" + commentsid)
    .then((response) => response.data)
    .then((response) => {setComments(response)});
    }
    else{
        alert("You can't delete other people's commments")
    }
  }


  const searchComments = async (commentssearchword) => {
    console.log(commentssearchword);
    const response = await axios.get(baseURL + "searchComments?paramword=" + commentssearchword)
    setComments(response.data);
    //setSearchResult(response.data);
    console.log(response.data);
  //   console.log(searchResult);
  
  }

  const goUpdateBoard = () => {
  if(currentuser === board.reg_id){
    navigate ('/dtlViewUpdateForm/' + boardid)
  }
  else{
    //setError(true);
    alert("You can't update other people's posts")
  }
}

 const goUpdateComment = (commentsid, comreg_id) => {
  console.log(commentsid);
    console.log(comreg_id);
  if(currentuser === comreg_id){
    navigate ('/commentsUpdate/' + boardid + '/' + commentsid)
  }
  else{
    //setError(true);
    alert("You can't update other people's posts")
  }
}


    return (
        <div>    
            <DetailHeader searchComments={searchComments}/>
             <Container>
              <Card style={{backgroundColor: "darkslategray"}}> 
               <Card.Body>
               <h4 style={{color: "white"}}>Full Post</h4>
                <Table>
                    <thead>
                    <tr>
                        <th colspan="5">{board.title}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="5">{board.content}</td>
                    </tr>	 
                    <tr>
                        <td>#{board.boardid}</td>
                        <td>Posted by {board.reg_id}</td>
                        <td>Posted {board.reg_date}</td>
                        <td><Button variant="dark" onClick={() => goUpdateBoard(boardid, board.reg_id)}>Update</Button></td>
                        <td><Link to={'/commentInsert/' + boardid} className="btn btn-dark">Add Comment</Link></td>
                    </tr>
                    </tbody>	   
                </Table>
               </Card.Body>
              </Card>

                <br/>
                <hr/>

              <h4>Comments</h4>
              {comments.map((comment) => (<Card style={{backgroundColor: "rgb(38, 106, 106)"}} key={comment.commentsid} comment={comment} >
               {/* <Card style={{backgroundColor: "rgb(38, 106, 106)"}}> */}
               <Card.Body>
                <Table> 
                    <thead>
                    <tr>
                        <td colspan="5">{comment.comm_content}</td>
                    </tr>	 
                    </thead>
                    <tbody>
                    <tr>
                    {/* {comments.map((comment) => (<tr key={comment.commentsid} comment={comment}> */}
                        <td>#{comment.commentsid}</td>
                        <td>Posted by {comment.comreg_id}</td>
                        <td>Posted {comment.reg_date}</td>
                        <td><Button variant="dark" onClick={() => goUpdateComment(comment.commentsid, comment.comreg_id)}>Update</Button></td>
                        <td><Button variant="dark" onClick={() => deleteComment(boardid, comment.commentsid, comment.comreg_id)}>Delete</Button></td>
                    </tr>
                   {/* ))} */}
                    </tbody>	   
                </Table>
               </Card.Body>
              </Card>
            ))} 
              <br/>
            </Container>
            <br/>
        </div>
    );
};





export default DetailView;