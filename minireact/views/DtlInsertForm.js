import React, { useState } from 'react';
import axios from 'axios';
import { Button, Form } from 'react-bootstrap';
//import {withRouter} from 'react';
//import { withRouter } from 'react-router-dom';
//import { useNavigate } from 'react-router';
import { useNavigate, useParams } from "react-router-dom";
//import PropTypes from "prop-types";


const DtlInsertForm = (props) => { 

    const {mst_cd}  = useParams();

    const [entry, setEntry] = useState({
        dtl_cd: "",
        dtl_name: "",
        dtl_notes: "",
        //mst_result: "",
        //error: Boolean
    });


    const changeValue = (e) => {
        setEntry({
            ...entry,
            [e.target.name]:e.target.value
        });
    }

    const baseURL = "http://localhost:8080/api/";
    let navigate = useNavigate(); 

    const submitInsertDtl = (e) => {
        e.preventDefault();
        axios.post(baseURL + "insertDtl?mst_cd=" + mst_cd + "&dtl_cd=" + entry.dtl_cd + "&dtl_name=" + entry.dtl_name + "&dtl_notes=" + entry.dtl_notes)
        .then((res) => { const response = res.data;
          console.log(res);
          if(response === 'success'){
              console.log("res====>", response);
              navigate('/home');
          } else{
              console.log("res===codetaken===>", response);
              //item.error = true;
              document.getElementById('error').style.visibility = 'visible';
            }})
        }

    return (

        <Form onSubmit={submitInsertDtl}>
        {/* <Form.Title>Enter Detail for Item with Master Code {mst_cd}</Form.Title> */}
        <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Detail Code</Form.Label>
              <Form.Control type="text" placeholder="Enter Detail Code" onChange={changeValue} name="dtl_cd"/>
              {/* <Form.Control type="text" placeholder="Enter Title"  name="title" ref="title"/> */}
                <Form.Text id="error" style={{color: "red" , visibility: 'hidden'}}>
                This detail code is taken
                </Form.Text>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Name</Form.Label>
              <Form.Control type="text" placeholder="Enter Name" onChange={changeValue} name="dtl_name"/>
              {/* <Form.Control type="text" placeholder="Enter Title"  name="title" ref="title"/> */}
                <Form.Text className="text-muted">
                Make it pretty; it won't format itself automatically
                </Form.Text>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Notes</Form.Label>
              <Form.Control type="text" placeholder="Enter Notes" onChange={changeValue} name="dtl_notes"/>
              {/* <Form.Control type="text" placeholder="Enter Author"  name="author" ref="author"/> */}
            </Form.Group>
      
            <Button variant="primary" type="submit">
             Submit
            </Button>
        </Form>
      
    );

    };


export default DtlInsertForm;