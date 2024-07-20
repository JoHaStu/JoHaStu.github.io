import React, { useEffect, useState } from 'react';
import { Button, Form, Container } from 'react-bootstrap';
import { useNavigate, useParams } from "react-router-dom";
import axios from 'axios';
import FormBar from '../components/FormBar';

const Update = () => {
   
    let {prodcode}  = useParams();
    
    const [update, setUpdate] = useState({
        prodcode: "",
        prodname: "",
        lotno: "",
        qty: "",
        proddate: "",
        enddate: "",
    });
    
    const baseURL = "http://localhost:8080/api/";

    useEffect(() => {
        axios.get(baseURL + "updateData?prodcode=" + prodcode)
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
        axios.post(baseURL + "update", {
            prodcode: prodcode,
            prodname: update.prodname,
            lotno: update.lotno,
            qty: update.qty,
            proddate: update.proddate,
            enddate: update.enddate,
        })
        .then((response) => {
            console.log("result", response);
            setUpdate(response.data)}) 
            navigate('/home');
    };
    
   return (
    <div>
        <FormBar />
        <Container style={{width: "300px", marginLeft: "5%"}}>
        <Form onSubmit={submitUpdate}>
           
        <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Product Name</Form.Label>
              <Form.Control type="text" placeholder="Update product name" onChange={changeValue} name="prodname" value={update.prodname}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Lot Number</Form.Label>
              <Form.Control type="text" placeholder="Update lot number" onChange={changeValue} name="lotno" value={update.lotno}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Quantity</Form.Label>
              <Form.Control type="number" placeholder="Update quantity" onChange={changeValue} name="qty" value={update.qty}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Production Date</Form.Label>
              <Form.Control type="date" onChange={changeValue} name="proddate" value={update.proddate}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>End Date</Form.Label>
              <Form.Control type="date" onChange={changeValue} name="enddate" value={update.enddate}/>
            </Form.Group>
      
            <Button variant="primary" type="submit">Update</Button>         
        </Form>
        </Container>
        </div>
   );
};


export default Update;