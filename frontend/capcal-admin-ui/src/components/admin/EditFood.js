import React from "react";
import api from "../../api/api";

class EditFood extends React.Component {
      constructor(props){
        super(props);
        this.state={
            id:"",
            foodName:"",
            intakeDate:"",
            calories:"",
            username:""            
        }       
        
        const getFoodData=async ()=>{
            const id=(window.location.pathname.split("/")[2]);
            const response = await api.get("/foodlog/"+id);
            this.setState(response.data);
        }
        getFoodData();
    }

    update=(e)=>{
        e.preventDefault();
        if(this.name==="") return;
        this.props.updateFoodHandler(this.state);
        this.setState({id:"",foodName:"",intakeDate:"",calories:"",username:""})  
        this.props.navigateBackHandler("/"); 
    }

    render() {
        return (
            <div className="ui main">
                <h2>Edit food</h2>
                <form className="ui form" onSubmit={this.update}>
                    <div className="field">
                        <label>Name</label>
                        <input type="text" name="name" id="name" placeholder="Whad did you eat?"
                            onChange={(e) => this.setState({ foodName: e.target.value })}
                            value={this.state.foodName} required/>
                    </div>
                    <div className="field">
                        <label>Date</label>
                        <input type="datetime-local" id="date" name="date" onChange={(e) => this.setState({ intakeDate: e.target.value })}
                            value={this.state.intakeDate} required/>
                    </div>
                    <div className="field">
                        <label>Calories</label>
                        <input type="number" id="calories" name="calories" onChange={(e) => this.setState({ calories: e.target.value })}
                            value={this.state.calories} required/>
                    </div>
                    <div className="field">
                        <label>User</label>
                        <input type="text" name="user" id="user" placeholder="User"
                            onChange={(e) => this.setState({ username: e.target.value })}
                            value={this.state.username} required/>
                    </div>
                    
                        <button className="ui button blue">Update</button>
                    
                </form>
            </div>
        );
    }
}
export default EditFood;