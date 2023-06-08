import React from "react";
import Food from "./Food"
import { Link } from "react-router-dom"
import loggedinuser from "../../api/user"; 

const FoodList = (props) => {
    const renderFoodList = props.foodlist.map((fooditem) => {
        return (
            <Food food={fooditem} />
        );
    });

    const search = (e) => {
        e.preventDefault();
        const searchdata = {
            fromdate: e.target.elements.fromdate.value,
            todate: e.target.elements.todate.value, user: loggedinuser.username
        };
        props.searchHandler(searchdata);
    }

    return (
        <div>
            <h2>Food log</h2>
            <div className="ui main" style={{marginTop:"40px"}}>
                <form className="ui form" onSubmit={search}>
                    <div className="field">
                        <label>From Date</label>
                        <input type="date" id="fromdate" name="fromdate" />
                    </div>
                    <div className="field">
                        <label>To Date</label>
                        <input type="date" id="todate" name="todate" />
                    </div>
                    <div className="field">
                        <button className="ui button blue left">Search</button>
                        <Link to="/add">
                            <button className="ui button blue right">Add Food</button>
                        </Link>
                        <Link to="/report">
                            <button className="ui button blue left">My daily report.</button>
                        </Link>
                    </div>
                </form>
            </div>
            <div className="ui celled list">
                {renderFoodList}
            </div>
        </div>
    );
}

export default FoodList;