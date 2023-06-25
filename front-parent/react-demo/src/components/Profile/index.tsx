import React from 'react';

interface ProfileProps {
  name?: string;
  isPacked?: boolean;
}

// 脚手架示例组件
const Profile: React.FC<ProfileProps> = ({ isPacked = false, name = 'yu' }) => {
  return (
    <>
      {name} {isPacked && '✔'}
      <img
        style={{ width: 30, height: 30 }}
        src="http://192.168.241.106:8083/mh-wash-invocationlab-erd-online-view/logo.svg"
        alt="Katherine Johnson"
        onClick={(e) => alert(e.target)}
      />
    </>
  );
};

export default Profile;
