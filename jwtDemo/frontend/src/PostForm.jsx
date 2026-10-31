import { useActionState } from "react";
import SubmitButton from "./SubmitButton";
import api from "./API";

const PostForm = ({ onCreated }) => {
  const submitAction = async (prev, formData) => {
    const title = formData.get("title")?.trim();
    const content = formData.get("content")?.trim();

    if (!title || !content) {
      return {
        error: "Title and content are required.",
        msg: "",
      };
    }

    try {
      const res = await api.post("/posts", { title, content });
      if (onCreated) {
        onCreated(res.data);
      }
      return {
        error: "",
        msg: "Post created successfully!",
      };
    } catch (error) {
      return {
        error:
          error.response?.data?.message ||
          error.response?.data?.error ||
          "Failed to create post.",
        msg: "",
      };
    }
  };

  const [state, formAction] = useActionState(submitAction, {
    error: "",
    msg: "",
  });

  return (
    <div className="w-full text-left">
      <h3 className="text-lg font-bold text-gray-900 mb-3">Create a Post</h3>

      {state?.error && (
        <div className="mb-3 p-2.5 text-xs text-red-600 bg-red-50 border border-red-200 rounded-md">
          {state.error}
        </div>
      )}

      {state?.msg && (
        <div className="mb-3 p-2.5 text-xs text-green-600 bg-green-50 border border-green-200 rounded-md">
          {state.msg}
        </div>
      )}

      <form action={formAction} className="space-y-3">
        <div>
          <label className="block text-xs font-medium text-gray-700 mb-1">
            Title
          </label>
          <input
            type="text"
            name="title"
            placeholder="Enter title"
            required
            className="w-full px-3 py-2 border border-gray-300 rounded-md text-sm text-gray-900 placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <div>
          <label className="block text-xs font-medium text-gray-700 mb-1">
            Content
          </label>
          <textarea
            name="content"
            placeholder="Write your post content..."
            rows={3}
            required
            className="w-full px-3 py-2 border border-gray-300 rounded-md text-sm text-gray-900 placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <SubmitButton label="Create Post" />
      </form>
    </div>
  );
};

export default PostForm;
