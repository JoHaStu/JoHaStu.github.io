import React, { useState } from 'react';
import axios from 'axios';
import { Button, Form } from 'react-bootstrap';
//import {withRouter} from 'react';
//import { withRouter } from 'react-router-dom';
//import { useNavigate } from 'react-router';
import { useNavigate } from "react-router-dom";
//import PropTypes from "prop-types";


const InsertForm = (props) => { 

    const [item, setItem] = useState({
        mst_cd: "",
        mst_name: "",
        mst_notes: "",
        //mst_result: "",
        //error: Boolean
    });


    const changeValue = (e) => {
        setItem({
            ...item,
            [e.target.name]:e.target.value
        });
    }

    const baseURL = "http://localhost:8080/api/";
    let navigate = useNavigate(); 

    const submitInsert = (e) => {
        e.preventDefault();
        axios.post(baseURL + "insertMst?mst_cd=" + item.mst_cd + "&mst_name=" + item.mst_name + "&mst_notes=" + item.mst_notes 
        // {
        //     mst_cd: item.mst_cd,
        //     mst_name: item.mst_name,
        //     mst_notes: item.mst_notes,
        //     mst_result: item.mst_result
        //   }
          )
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

        <Form onSubmit={submitInsert}>
        {/* <Form.Title>Insert Item into Master List</Form.Title> */}
        <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Master Code</Form.Label>
              <Form.Control type="text" placeholder="Enter Master Code" onChange={changeValue} name="mst_cd"/>
              {/* <Form.Control type="text" placeholder="Enter Title"  name="title" ref="title"/> */}
                <Form.Text id="error" style={{color: "red" , visibility: 'hidden'}}>
                This Mastercode is taken
                </Form.Text>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Name</Form.Label>
              <Form.Control type="text" placeholder="Enter Name" onChange={changeValue} name="mst_name"/>
              {/* <Form.Control type="text" placeholder="Enter Title"  name="title" ref="title"/> */}
                <Form.Text className="text-muted">
                Make it pretty; it won't format itself automatically
                </Form.Text>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Notes</Form.Label>
              <Form.Control type="text" placeholder="Enter Notes" onChange={changeValue} name="mst_notes"/>
              {/* <Form.Control type="text" placeholder="Enter Author"  name="author" ref="author"/> */}
            </Form.Group>
      
            <Button variant="primary" type="submit">
             Submit
            </Button>
        </Form>
      
    );

    };

export default InsertForm;