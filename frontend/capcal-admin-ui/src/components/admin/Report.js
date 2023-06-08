import React,{ useEffect } from "react";
import DataTable from "react-data-table-component"
import { Link } from "react-router-dom";
import api from "../../api/api"

const Report = (props) => {

    const columns = [
        {
            name: 'Username',
            selector: row => row.username,
            sortable: true,
        },
        {
            name: 'Current week entries',
            selector: row => row.currentweek,
            sortable: true,
        },
        {
            name: 'Last week entries',
            selector: row => row.previousweek,
            sortable: true,
        },
        {
            name: 'Average(cal.) in a week',
            selector: row => row.averageinaweek,
            sortable: true,
        }
    ];

    return (
        <div className="ui celled table" style={{marginTop:"50px"}}>
        <DataTable
            columns={columns}
            data={props.admindata}
            pagination
        />
        <Link to="/">
        <button className="ui button blue left">Back to home</button>
        </Link>
        </div>

                
    );
}

export default Report;