export interface FullPost {
  username: string,
  email: string,
  post: Post,
  postImages : PostImage[]
}
interface Post {
  id: number;
  author: number;
  title: string;
  text: string;
  date: string;
}
interface PostImage {
  id: number;
  postId: number;
  image: string;
}
