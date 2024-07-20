import { Table } from "react-bootstrap";
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from "react-router-dom";


const InventoryList = () => {

   return (
    <div>
    <Table striped bordered hover>
            <thead>
                <tr>
                <th>Product Code</th>
             <th>Product Name</th>
             <th>Lot Number</th>
             <th>Quantity</th>
             <th>Production Date</th>
             <th>End Date</th>
             <th>Update</th>
             <th>Delete</th>
                </tr>
            </thead>
            <tbody>
            <tr>
             <td>{prodcode}</td>  
             <td>{prodname}</td>  
             <td>{lotno}</td>
             <td>{qty}</td>
             <td>{proddate}</td>
             <td>{enddate}</td>   
             <td><button >Update</button></td>
             <td><button >Delete</button></td> 
                </tr>
            </tbody>
        </Table>

</div>

  




    );
};

export default InventoryList;