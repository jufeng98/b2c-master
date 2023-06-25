import React from 'react';
import Profile from '../Profile';

interface GalleryProps {
  color?: string;
  time: string;
}

// 脚手架示例组件
const Gallery: React.FC<GalleryProps> = ({ color = 'lightcoral', time }) => {
  const people = [
    {
      id: 0,
      name: '凯瑟琳·约翰逊',
      profession: '数学家',
    },
    {
      id: 1,
      name: '马里奥·莫利纳',
      profession: '化学家',
    },
    {
      id: 2,
      name: '穆罕默德·阿卜杜勒·萨拉姆',
      profession: '物理学家',
    },
    {
      name: '珀西·莱温·朱利亚',
      profession: '化学家',
    },
    {
      name: '苏布拉马尼扬·钱德拉塞卡',
      profession: '天体物理学家',
    },
  ];
  const listItems = people.map((person) => (
    <li key={person.id}>
      <p>
        <b>{person.name}:</b>
        {' ' + person.profession + ' '}因{person.profession}而闻名世界
      </p>
    </li>
  ));

  return (
    <section>
      <h1>了不起的科学家</h1>
      <h1 style={{ color: color }}>{time}</h1>
      <Profile />
      <Profile isPacked={true} />
      <Profile />
      <ul>{listItems}</ul>
    </section>
  );
};

export default Gallery;
