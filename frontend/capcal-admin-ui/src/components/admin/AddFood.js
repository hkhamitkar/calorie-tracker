import React from "react";

class AddFood extends React.Component {
    state = {
        id:"",
        foodName: "",
        intakeDate: "",
        calories: "",
        username:""
    }

    add=(e)=>{
        e.preventDefault();
        if(this.name==="") return;
        this.props.addFoodhandler(this.state);
        this.setState({id:"",foodName:"",intakeDate:"",calories:"",username:""})  
        this.props.navigateBackHandler("/"); 
    }

    render() {
        return (
            <div className="ui main">
                <h2>Add food</h2>
                <form className="ui form" onSubmit={this.add}>
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
                    
                        <button className="ui button blue">Add</button>
                    
                </form>
            </div>
        );
    }
}
export default AddFood;