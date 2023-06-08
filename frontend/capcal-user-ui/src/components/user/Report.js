import React from "react";
import DataTable from "react-data-table-component"
import { Link } from "react-router-dom";
import loggedinuser from "../../api/user"

const Report = (props) => {

    const columns = [
        {
            name: 'Date',
            selector: row => row.days,
            sortable: true,
        },
        {
            name: 'Calories',
            selector: row => row.calories,
            sortable: true,
            right: true,
            conditionalCellStyles: [
                {
                    when: row => row.calories < loggedinuser.callimit*2/3,
                    style: {
                        backgroundColor: 'rgba(63, 195, 128, 0.9)',
                        color: 'white',
                        '&:hover': {
                            cursor: 'pointer',
                        },
                    },
                },
                {
                    when: row => row.calories >= loggedinuser.callimit*2/3 && row.calories < loggedinuser.callimit,
                    style: {
                        backgroundColor: 'rgba(248, 148, 6, 0.9)',
                        color: 'white',
                        '&:hover': {
                            cursor: 'pointer',
                        },
                    },
                },
                {
                    when: row => row.calories >= loggedinuser.callimit,
                    style: {
                        backgroundColor: 'rgba(242, 38, 19, 0.9)',
                        color: 'white',
                        '&:hover': {
                            cursor: 'not-allowed',
                        },
                    },
                },
            ],
        },
        // {
        //     name: 'Remark',
        //     selector: row => row.calstatus,
        //     sortable: true,
        // },
    ];

    return (
        <div className="ui celled table" style={{marginTop:"80px"}}>
        <DataTable
            columns={columns}
            data={props.userstats}
            pagination
        />
        <Link to="/">
        <button className="ui button blue left">Back to home</button>
        </Link>
        </div>

                
    );
}

export default Report;