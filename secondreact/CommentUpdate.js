import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Button, Form, Container } from 'react-bootstrap';
//import {withRouter} from 'react';
//import { withRouter } from 'react-router-dom';
//import { useNavigate } from 'react-router';
import { useNavigate, useParams } from "react-router-dom";
//import PropTypes from "prop-types";
import FormHeader from '../components/FormHeader';


const CommentUpdate = (props) => { 

    const baseURL = "http://localhost:8080/api/";
    const {commentsid, boardid}  = useParams();
    const currentuser = sessionStorage.getItem("id");

    const [comupdate, setComupdate] = useState({
        //boardid: "",
        //commetnsid: "",
        comm_content: "",
        //comreg_id: "",
       // reg_date: Date
    });

    useEffect(() => {
        axios.get(baseURL + "updateCommentForm?commentsid=" + commentsid)
        //.then((res) => res.json())
        .then((res) =>{
            setComupdate(res.data);
        });
        }, []);

    const changeValue = (e) => {
        setComupdate({
            ...comupdate,
            [e.target.name]:e.target.value
        });
    }

    
    let navigate = useNavigate(); 

    const submitComUpdate = (e) => {
        e.preventDefault();
       axios.post(baseURL + "updateComment?boardid=" + boardid + "&commentsid=" + commentsid + "&comm_content=" + comupdate.comm_content
       // + "&reg_id=" + currentuser
       )
     
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
        <Form onSubmit={submitComUpdate}>
        {/* <Form.Title>Insert Item into Master List</Form.Title> */}
        <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Comment</Form.Label>
              <Form.Control type="text" onChange={changeValue} name="comm_content" value={comupdate.comm_content}/>     
            </Form.Group>
      
            <Button variant="primary" type="submit">
             Update
            </Button>
        </Form>
        </Container>
      </div>
    );

    };

export default CommentUpdate;