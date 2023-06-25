import Gallery from '@/components/Gallery';
import Guide from '@/components/Guide';
import { trim } from '@/utils/format';
import { PageContainer } from '@ant-design/pro-components';
import { useModel } from '@umijs/max';
import { useState } from 'react';
import styles from './index.less';

const HomePage: React.FC = () => {
  const { name } = useModel('global');
  const [time, setTime] = useState<string>('');

  setInterval(() => {
    let now = new Date();
    setTime(now.getHours() + ':' + now.getMinutes() + ':' + now.getSeconds());
  }, 1000);

  return (
    <>
      <PageContainer ghost>
        <div className={styles.container}>
          <Guide name={trim(name)} />
        </div>
      </PageContainer>
      <Gallery time={time} />
    </>
  );
};

export default HomePage;
