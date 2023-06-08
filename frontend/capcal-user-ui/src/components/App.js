import React, { useState, useEffect } from "react";
import { Routes, Route, useNavigate } from "react-router-dom"
import "./App.css"
import Header from "./user/Header"
import AddFood from "./user/AddFood"
import FoodList from "./user/FoodList"
import api from "../api/api"
import Report from "./user/Report";
import loggdinuser from "../api/user";
import Invite from "./user/Invite"



function App() {
  const LOCAL_STORAGE_TOKEN_KEY = "access_token";
  const [foodlist, setFoodList] = useState([]);
  const [userstats, setUserStats] = useState([]);

  const addFoodhandler = async (food) => {
    //food.id=uuid();
    const request = food;
    const response = await api.post("/foodlog/add", request)
    setFoodList([response.data, ...foodlist]);
  }


  const searchHandler = async (searchdata) => {
    const { fromdate, todate, user } = searchdata;
    if ((fromdate && todate) || user) {
      let url = "/foodlog/find/0/100/intakeDate?";
      if (fromdate) url = url + "fromDate=" + fromdate;
      if (todate) url = url + "&toDate=" + todate;
      if (user) url = url + "&username=" + user;
      const response = await api.get(url);
      setFoodList(response.data.content);
    } else {
      retrieveFoodList();
    }
  }

  const retrieveFoodList = async () => {
    const response = await api.get("/foodlog/find/0/100/intakeDate?username=" + loggdinuser.username);
    return response.data.content;
  }

  const navigateBackHandler = (url) => {
    navigate(url);
  }

  const navigate = useNavigate();
  useEffect(() => {
    const getUserFoodList = async () => {
      const allfoodList = await retrieveFoodList();
      if (foodlist) setFoodList(allfoodList);
    }
    getUserFoodList();

  }, []);

  useEffect(() => {
    const getUserReport = async () => {
      const response = await api.get("/foodlog/"+loggdinuser.username+"/report"); 
      if (response.data) setUserStats(response.data);
    }
    getUserReport();
  }, []);


  return (
    <div>
      <Header />
      <Routes>
        <Route exact path="/" element={
          <FoodList foodlist={foodlist} searchHandler={searchHandler} />} />
        <Route path="/add" element={<AddFood addFoodhandler={addFoodhandler} navigateBackHandler={navigateBackHandler} />} />
        <Route path="/report" element={<Report userstats={userstats} navigateBackHandler={navigateBackHandler} />} />
        <Route path="/invite" element={<Invite navigateBackHandler={navigateBackHandler} />} />
      </Routes>
      {/* <Header />
      <FoodList foodlist={foodlist} getFoodId={removeFoodHandler} />
      <AddFood addFoodhandler={addFoodhandler} /> */}
    </div>
  );
}
export default App;
