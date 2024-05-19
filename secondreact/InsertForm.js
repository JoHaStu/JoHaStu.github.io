import React, { useState } from 'react';
import axios from 'axios';
import { Button, Form, Container } from 'react-bootstrap';
//import {withRouter} from 'react';
//import { withRouter } from 'react-router-dom';
//import { useNavigate } from 'react-router';
import { useNavigate } from "react-router-dom";
//import PropTypes from "prop-types";
import FormHeader from '../components/FormHeader';
import Session from 'react-session-api';


const InsertForm = (props) => { 

   let currentuser = Session.get("currentuser");
    

    const [board, setBoard] = useState({
        boardid: "",
        title: "",
        content: "",
        reg_id: currentuser,
        reg_date: Date
    });


    const changeValue = (e) => {
        setBoard({
            ...board,
            [e.target.name]:e.target.value
        });
    }

    const baseURL = "http://localhost:8080/api/";
    let navigate = useNavigate(); 

    const submitInsert = (e) => {
        e.preventDefault();
        axios.post(baseURL + "insertBoard?title=" + board.title + "&content=" + board.content 
        + "&reg_id=" + currentuser
      )
      
        // axios.post(baseURL + "insertBoard", {
        // title: board.title,
        // content: board.content,
        // reg_id: currentuser,
        // })
          
        .then((res) => {
            console.log("result", res);
                return res;             
            }) 
            navigate('/home');
         }
       

    return (
        <div>
        <FormHeader />
        <Container style={{marginTop: '30px'}}>
        <Form onSubmit={submitInsert}>
       
        <Form.Group className="mb-3" >
              <Form.Label>Title</Form.Label>
              <Form.Control type="text" placeholder="Enter a Title" onChange={changeValue} name="title"/>
            </Form.Group>

            <Form.Group className="mb-3" >
              <Form.Label>Content</Form.Label>
              <Form.Control type="text" placeholder="Tell us about it..." onChange={changeValue} name="content"/>
            </Form.Group>

      
            <Button variant="primary" type="submit">
             Submit
            </Button>
        </Form>
        </Container>
        </div>
    );

    };

export default InsertForm;