import React, { useState } from 'react'

function Registration() {
    const [empId, setEmpId] = useState();
    const [EmpName, setEmpName] = useState();
    const [EmpDesignation, setEmpDesignation] = useState();
    const [EmpStack, setEmpStack] = useState();
    const [email, setemail] = useState();
    const [password, setPassword] = useState();



    async function sendData(e) {
        e.preventDefault();
        try {
            const server = await fetch("http://localhost:8080/v1/user/addemp", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    emp_id: Number(empId),
                    emp_name: EmpName,
                    email: email,
                    designation: EmpDesignation,
                    stack: EmpStack,
                    password: password
                })
            });
            const statue = await server.text();
            alert(statue);

        } catch (err) {
            console.log(err);
        }
    }


    return (
        <div>
            <form onSubmit={sendData}>
                <div class="form-group">
                    <label for="exampleInputEmail1">EmpId</label>
                    <input type="number" onChange={(e) => setEmpId(e.target.value)} class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter emp_id" required />

                </div>

                <div class="form-group">
                    <label for="exampleInputEmail1">Name</label>
                    <input type="text" onChange={(e) => setEmpName(e.target.value)} class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter name" required />

                </div>

                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input type="text" onChange={(e) => setemail(e.target.value)} class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email address" required />
                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                </div>

                <div class="form-group">
                    <label for="exampleInputEmail1">Designations</label>
                    <input type="text" onChange={(e) => setEmpDesignation(e.target.value)} class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Designation" required />

                </div>

                <div class="form-group">
                    <label for="exampleInputEmail1">Tech stack</label>
                    <input type="text" onChange={(e) => setEmpStack(e.target.value)} class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter tech stack" required />

                </div>

                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" onChange={(e) => setPassword(e.target.value)} class="form-control" id="exampleInputPassword1" placeholder="Password" required />
                </div>

                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1" />
                    <label class="form-check-label" for="exampleCheck1">Check me out</label>
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
            </form>
        </div>
    )
}

export default Registration