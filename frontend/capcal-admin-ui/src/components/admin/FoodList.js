import React from "react";
import Food from "./Food"
import { Link } from "react-router-dom"

const FoodList = (props) => {
            
    const deleteFoodlHandler = (id) => {
        props.getFoodId(id);
    };
    const renderFoodList = props.foodlist.map((fooditem) => {
        return (
            <Food food={fooditem} clickHandler={deleteFoodlHandler} key={fooditem.id} />
        );
    });

    const search = (e) => {
        e.preventDefault();
        const searchdata = {
            fromdate: e.target.elements.fromdate.value,
            todate: e.target.elements.todate.value, user: e.target.elements.user.value
        };
        props.searchHandler(searchdata);
    }

    return (
        <div>
            <h2>Food log</h2>
            <div className="ui main">
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
                        <label>User</label>
                        <input type="input" id="user" name="user" size="9" />
                    </div>
                    <div className="field">
                        <button className="ui button blue left">Search</button>
                        <Link to="/add">
                            <button className="ui button blue right">Add Food</button>
                        </Link>
                        <Link to="/report">
                            <button className="ui button blue left">Weekly Report</button>
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