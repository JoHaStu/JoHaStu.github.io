import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Button, Form, Container } from 'react-bootstrap';
//import {withRouter} from 'react';
//import { withRouter } from 'react-router-dom';
//import { useNavigate } from 'react-router';
import { useNavigate, useParams } from "react-router-dom";
//import PropTypes from "prop-types";
import FormHeader from '../components/FormHeader';
import Session from 'react-session-api';


const UpdateForm = (props) => { 

    const baseURL = "http://localhost:8080/api/";
    const {boardid}  = useParams();
    let currentuser = Session.get("currentuser");

    const [update, setUpdate] = useState({
        boardid: "",
        title: "",
        content: "",
        reg_id: "",
        reg_date: Date
    });

    useEffect(() => {
        axios.get(baseURL + "updateBoardForm?boardid=" + boardid)
        //.then((res) => res.json())
        .then((res) =>{
            setUpdate(res.data);
        });
        }, []);

    const changeValue = (e) => {
        setUpdate({
            ...update,
            [e.target.name]:e.target.value
        });
    }

    
    let navigate = useNavigate(); 

    const submitUpdate = (e) => {
        e.preventDefault();
       axios.post(baseURL + "updateBoard?boardid=" + boardid + "&title=" + update.title + "&content=" + update.content
        //+ "&reg_id=" + currentuser
       )
      
        // axios.post(baseURL + "updateBoard", {
        // title: update.title,
        // content: update.content,
        // reg_id: currentuser,
        // })
          
        .then((res) => {
            console.log("result", res);
                return res;             
            }) 
            navigate('/detailView/' + boardid);
         }

    return (
    <div>
        <FormHeader />
        <Container>
        <Form onSubmit={submitUpdate}>
        {/* <Form.Title>Insert Item into Master List</Form.Title> */}
        <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Title</Form.Label>
              <Form.Control type="text" onChange={changeValue} name="title" value={update.title}/>     
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Name</Form.Label>
              <Form.Control type="text" onChange={changeValue} name="content" value={update.content}/>  
            </Form.Group>
      
            <Button variant="primary" type="submit">
             Update
            </Button>
        </Form>
        </Container>
    </div>  
    );

    };

export default UpdateForm;