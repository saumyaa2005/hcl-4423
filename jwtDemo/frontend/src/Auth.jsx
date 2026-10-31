import api from "./API";
import SubmitButton from "./SubmitButton";
import { useState, useActionState } from "react";

const AuthForm = ({ onLogin }) => {
  const [mode, setMode] = useState("login");

  const submitAction = async (prevState, formData) => {
    const username = formData.get("username")?.trim();
    const password = formData.get("password")?.trim();

    if (!username || !password) {
      return {
        error: "Username and password are required.",
        msg: "",
        username: username || ""
      };
    }

    try {
      if (mode === "register") {
        const res = await api.post("/auth/register", { username, password });
        setMode("login");
        return {
          error: "",
          msg: res.data?.message || "Registration successful! You can now sign in.",
          username: ""
        };
      } else {
        const res = await api.post("/auth/login", { username, password });
        const token = res.data?.token;
        if (token) {
          localStorage.setItem("token", token);
          localStorage.setItem("user", JSON.stringify(res.data));
        }
        if (onLogin) {
          onLogin(res.data);
        }
        return {
          error: "",
          msg: "Signed in successfully!",
          username: ""
        };
      }
    } catch (error) {
      return {
        error:
          error.response?.data?.message ||
          error.response?.data?.error ||
          "Invalid credentials. Please try again.",
        msg: "",
        username: username || ""
      };
    }
  };

  const [state, formAction] = useActionState(submitAction, {
    error: "",
    msg: "",
    username: ""
  });

  return (
    <div className="w-full">
      <div className="mb-6 text-center">
        <h2 className="text-2xl font-bold text-gray-900">
          {mode === "login" ? "Sign In" : "Create Account"}
        </h2>
        <p className="mt-1 text-sm text-gray-500">
          {mode === "login"
            ? "Enter your credentials to access your account"
            : "Enter your details to register a new account"}
        </p>
      </div>

      {state?.error && (
        <div className="mb-4 p-3 rounded-md bg-red-50 border border-red-200 text-sm text-red-600">
          {state.error}
        </div>
      )}

      {state?.msg && (
        <div className="mb-4 p-3 rounded-md bg-green-50 border border-green-200 text-sm text-green-600">
          {state.msg}
        </div>
      )}

      <form action={formAction} className="space-y-4 text-left">
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Username
          </label>
          <input
            type="text"
            name="username"
            defaultValue={state?.username || ""}
            placeholder="Enter username"
            required
            className="w-full px-3 py-2 border border-gray-300 rounded-md text-sm text-gray-900 placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Password
          </label>
          <input
            type="password"
            name="password"
            placeholder="Enter password"
            required
            className="w-full px-3 py-2 border border-gray-300 rounded-md text-sm text-gray-900 placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <div className="pt-2">
          <SubmitButton label={mode === "login" ? "Sign In" : "Register"} />
        </div>
      </form>

      <div className="mt-6 text-center">
        <button
          type="button"
          onClick={() => setMode(mode === "login" ? "register" : "login")}
          className="text-sm font-medium text-blue-600 hover:text-blue-500 cursor-pointer"
        >
          {mode === "login"
            ? "Don't have an account? Sign up"
            : "Already have an account? Sign in"}
        </button>
      </div>
    </div>
  );
};

export default AuthForm;
