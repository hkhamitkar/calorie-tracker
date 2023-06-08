import React from "react";
import foodimg from "../../images/food.png"
import {Link} from "react-router-dom"

const Food=(props)=>{
    const{id,foodName,intakeDate,calories,username}=props.food;
    return (
        <div className="item">
            <img className="ui avatar image" src={foodimg} alt="food" />
                <div className="content">
                    <div className="header">{foodName}</div>
                    <div>Date & time: {intakeDate}</div>
                    <div>Calories: {calories}</div>
                    <div>User: {username}</div>
                </div>
              <i className="trash alternate outline icon"
              style={{color:"red", marginTop:"7px",marginLeft:"10px"}} 
              onClick={()=>props.clickHandler(id)}/> 
              <Link to={{pathname:"/edit/"+id, state:{food:props.food}}}>
              <i className="edit alternate outline icon"
              style={{color:"blue", marginTop:"7px"}}/> 
              </Link>
            </div>

    );
}

export default Food;