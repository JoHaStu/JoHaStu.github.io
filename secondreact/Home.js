import React, { useState, useEffect } from 'react';
import BoardList from '../components/BoardList';
import SessionHeader from '../components/SessionHeader';
import axios from 'axios';
import { useParams } from "react-router-dom";
import { Card, Container } from 'react-bootstrap';
import Session from 'react-session-api';


const Home = (props) => {
const [boards, setBoards] = useState([]);

let currentuser = Session.get("currentuser");

const baseURL = "http://localhost:8080/api/";

// useEffect(() => {
//     setBoards(props.searchResult)
// }, [props.searchResult])

useEffect(() => {
    axios.get(baseURL + "list")
    .then((response) => response.data)
    .then((response) => {setBoards(response)}, console.log(boards));
}, []) //empty array added to stop useEffect to keep endlessly rendering




const [boardid_del, setBoardid_del] = useState();
const [reg_id_del, setReg_id_del] = useState();

const deleteBoard = async (boardid_del, reg_id_del) => {
    setReg_id_del(reg_id_del);
    console.log(reg_id_del); 
    setBoardid_del(boardid_del);
    console.log(boardid_del);
    if(currentuser === reg_id_del){
   
    await axios.post(baseURL + "deleteBoard?boardid=" + boardid_del)
    //axios.get(baseURL + "list")
    .then((response) => response.data)
    .then((response) => {setBoards(response)},console.log(boards)); 
    }
    else{
        alert("You can't delete other people's posts")
    }
}

//const [searchResult, setSearchResult] = useState([]);

const searchBoard = async (boardsearchword) => {
  console.log(boardsearchword);
  const response = await axios.get(baseURL + "searchPosts?paramword=" + boardsearchword)
  setBoards(response.data);
  //setSearchResult(response.data);
  console.log(response.data);
//   console.log(searchResult);

}

    return (
        <div>    
            <SessionHeader searchBoard={searchBoard}/>
            <Container>
            {/* {boards.map((board) => (<BoardList key={board.boardid} board={board} deleteMst={deleteMst}/>))}  */}
            {boards.map((board) => (<BoardList key={board.boardid} board={board}  deleteBoard={deleteBoard} />))}
            </Container>
        </div>
    );
};





export default Home;