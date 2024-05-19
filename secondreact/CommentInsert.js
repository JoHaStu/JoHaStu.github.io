import React, { useState } from 'react';
import axios from 'axios';
import { Button, FloatingLabel, Form, Container } from 'react-bootstrap';
//import {withRouter} from 'react';
//import { withRouter } from 'react-router-dom';
//import { useNavigate } from 'react-router';
import { useNavigate, useParams } from "react-router-dom";
//import PropTypes from "prop-types";
import FormHeader from '../components/FormHeader';
import Session from 'react-session-api';


const CommentInsert = (props) => { 

    const {boardid}  = useParams();
    let currentuser = Session.get("currentuser");
   

    const [comment, setComment] = useState({
        //boardid: "",
        comm_content: "",
       // comreg_id: "",
        // reg_date: Date
    });


    const changeValue = (e) => {
        setComment({
            ...comment,
            [e.target.name]:e.target.value,
            
        });
    }

    const baseURL = "http://localhost:8080/api/";
    let navigate = useNavigate(); 

    const submitInsert = (e) => {
        e.preventDefault();
        axios.post(baseURL + "addComment?boardid=" + boardid + "&comm_content=" + comment.comm_content 
        + "&comreg_id=" + currentuser
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
<Container style={{marginTop: '30px'}}>
        <Form onSubmit={submitInsert}>
{/* 
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Content</Form.Label>
              <Form.Control type="text" placeholder="What did you think?" onChange={changeValue} name="comm_content"/>
            </Form.Group> */}

        <FloatingLabel controlId="floatingTextarea2" label="Comments">
        <Form.Control
          as="textarea"
          placeholder="What did you think?"
          style={{ height: '100px' }}
          onChange={changeValue}
          name="comm_content"
        />
      </FloatingLabel>
      
            <Button variant="primary" type="submit">
             Submit
            </Button>
        </Form>
        </Container>
        </div>
    );

    };

export default CommentInsert;