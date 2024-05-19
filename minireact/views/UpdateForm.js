import React, { useEffect, useState } from 'react';
import { Button, Form } from 'react-bootstrap';
import { useNavigate, useParams } from "react-router-dom";
import axios from 'axios';

const UpdateForm = (props) => {
    //const {mst_cd, mst_name, mst_notes} = props.item;
    ///const id = props.match.params.id;
    const {mst_cd}  = useParams();
    //const {id, title, author} = useParams();
    
    const [item, setItem] = useState({
        //mst_cd: "",
        mst_name: "",
        mst_notes: ""
    });
    
    const baseURL = "http://localhost:8080/api/";

    useEffect(() => {
        axios.get(baseURL + "updateMstForm?mst_cd=" + mst_cd)
        //.then((res) => res.json())
        .then((res) =>{
            setItem(res.data);
        });
        }, []);

    const changeValue = (e) => {
        setItem({
            ...item,
            [e.target.name]:e.target.value
        });
    }

   
    let navigate = useNavigate(); 

    const updateMst = (e) => {
        e.preventDefault();
        axios.post(baseURL + "updateMst?mst_cd=" + mst_cd + "&mst_name=" + item.mst_name + "&mst_notes=" + item.mst_notes
        //,{
            //method: "POST", 
            //headers: {"Content-Type": "multipart/form-data; boundary=something"}, 
            //body: JSON.stringify(book)
           // body: {title: book.title, author: book.author},
       // }
        )
        .then((res) => {
            console.log("result", res);
                return res;
                
              //return JSON.stringify(res);              
            }) 
            navigate('/home');
       // .then((res) => {
           // if (res === "success") {
                //navigate('/home');
          //  } else {
             //   alert('Update failed');
         //   }
           // });
    };
    
// 
    
   return (

        <Form onSubmit={updateMst} >
          {/* <Form.Title>Update Item {mst_cd}</Form.Title> */}
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Name</Form.Label>
              <Form.Control type="text" placeholder="Update Name" onChange={changeValue} name="mst_name" value={item.mst_name}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Notes</Form.Label>
              <Form.Control type="text" placeholder="Update Notes" onChange={changeValue} name="mst_notes" value={item.mst_notes}/>
            </Form.Group>
      
            <Button variant="primary" type="submit">
             Update
            </Button>
        </Form>
      
   );
};


export default UpdateForm;