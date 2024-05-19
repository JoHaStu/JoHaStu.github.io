import React, { useEffect, useState } from 'react';
import { Button, Form } from 'react-bootstrap';
import { useNavigate, useParams } from "react-router-dom";
import axios from 'axios';

const DtlUpdateForm = (props) => {

    ///const id = props.match.params.id;
    const {mst_cd, dtl_cd}  = useParams();
    //const {id, title, author} = useParams();
    
    const [entry, setEntry] = useState({
      //  mst_cd: "",
       // dtl_cd: "",
        dtl_name: "",
        dtl_notes: ""
    });
    
    const baseURL = "http://localhost:8080/api/";

    useEffect(() => {
        axios.get(baseURL + "updateDtlForm?mst_cd=" + mst_cd + "&dtl_cd=" + dtl_cd)
        .then((res) =>{
            setEntry(res.data);
            console.log(res.data);
        });
        }, []);

    const changeValue = (e) => {
        setEntry({
            ...entry,
            [e.target.name]:e.target.value
        });
    }


    let navigate = useNavigate(); 

    const updateDtl = (e) => {
        e.preventDefault();
        axios.post(baseURL + "updateDtl?mst_cd=" + mst_cd + "&dtl_cd=" + dtl_cd + "&dtl_name=" + entry.dtl_name + "&dtl_notes=" + entry.dtl_notes)
        .then((res) => {
            console.log("result", res);
              return res;              
            })
            navigate('/home');
            
    };
    

    
    return (

        <Form onSubmit={updateDtl} >
            {/* <Form.Title>Update Detail {dtl_cd} for Master Code {mst_cd}</Form.Title> */}
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Name</Form.Label>
              <Form.Control type="text" placeholder="Update Name" onChange={changeValue} name="dtl_name" value={entry.dtl_name} />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Notes</Form.Label>
              <Form.Control type="text" placeholder="Update Notes" onChange={changeValue} name="dtl_notes" value={entry.dtl_notes}/>
            </Form.Group>
      
            <Button variant="primary" type="submit">
             Update
            </Button>
        </Form>
      
    );
};


export default DtlUpdateForm;
