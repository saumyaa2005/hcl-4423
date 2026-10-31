import { useState, useEffect } from "react";
import AuthForm from "./Auth";
import PostForm from "./PostForm";

const App = () => {
  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
    const savedUser = localStorage.getItem("user");
    const token = localStorage.getItem("token");
    if (token && savedUser) {
      try {
        setCurrentUser(JSON.parse(savedUser));
      } catch {
        setCurrentUser({ username: "User", token });
      }
    }
  }, []);

  const handleLogin = (data) => {
    setCurrentUser(data);
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    setCurrentUser(null);
  };

  return (
    <div className="min-h-screen bg-gray-50 flex items-center justify-center p-4">
      <div className="w-full max-w-md bg-white p-8 rounded-xl shadow-sm border border-gray-200">
        {currentUser ? (
          <div className="space-y-5">
            <div className="flex justify-between items-center pb-4 border-b border-gray-200">
              <div>
                <h2 className="text-xl font-bold text-gray-900">
                  Welcome, {currentUser.username}!
                </h2>
                <p className="text-xs text-gray-500">Logged in with JWT</p>
              </div>

              <button
                onClick={handleLogout}
                className="py-1.5 px-3 rounded-md text-xs font-medium text-gray-700 bg-gray-100 hover:bg-gray-200 transition-colors cursor-pointer"
              >
                Sign Out
              </button>
            </div>

            {/* PostForm Component */}
            <PostForm onCreated={(newPost) => console.log("Created:", newPost)} />
          </div>
        ) : (
          <AuthForm onLogin={handleLogin} />
        )}
      </div>
    </div>
  );
};

export default App;