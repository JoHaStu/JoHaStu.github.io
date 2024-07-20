import React, { useState } from 'react';
import axios from 'axios';
import { Button, Container, Form } from 'react-bootstrap';
//import {withRouter} from 'react';
//import { withRouter } from 'react-router-dom';
//import { useNavigate } from 'react-router';
import { useNavigate } from "react-router-dom";
import FormBar from '../components/FormBar';
//import PropTypes from "prop-types";


const Insert = () => { 

    const [newItem, setNewItem] = useState({
        prodname: "",
        lotno: "",
        qty: "",
        proddate: "",
        enddate: ""
    });

    const {prodname, lotno, qty, proddate, enddate} = newItem;

    const changeValue = (e) => {
        setNewItem({
            ...newItem,
            [e.target.name]:e.target.value
        });
    }

    const baseURL = "http://localhost:8080/api/";
    let navigate = useNavigate(); 

    const submitInsert = (e) => {
        e.preventDefault();
        axios.post(baseURL + "insert", {
            prodname: newItem.prodname,
            lotno: newItem.lotno,
            qty: newItem.qty,
            proddate: newItem.proddate,
            enddate: newItem.enddate,
        })
        .then((res) => {const response = res.data;})
        navigate('/home');
        }

    return (
        
        <div>
             <FormBar />
             <Container style={{width: "300px", marginLeft: "5%"}}>
        <Form onSubmit={submitInsert}>
           
        <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Product Name</Form.Label>
              <Form.Control type="text" placeholder="Enter product name" onChange={(e)=>changeValue(e)} name="prodname" value={prodname}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Lot Number</Form.Label>
              <Form.Control type="text" placeholder="Enter lot number" onChange={(e)=>changeValue(e)} name="lotno" value={lotno}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Quantity</Form.Label>
              <Form.Control type="number" placeholder="Enter quantity" onChange={(e)=>changeValue(e)} name="qty" value={qty}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Production Date</Form.Label>
              <Form.Control type="date" onChange={(e)=>changeValue(e)} name="proddate" value={proddate}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>End Date</Form.Label>
              <Form.Control type="date" onChange={(e)=>changeValue(e)} name="enddate" value={enddate}/>
            </Form.Group>
      
            <Button variant="primary" type="submit">
             Submit
            </Button>        
            
        </Form>
</Container>
        </div>
    );

    };

export default Insert;