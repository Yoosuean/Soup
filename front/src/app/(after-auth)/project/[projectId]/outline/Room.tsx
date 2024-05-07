'use client'

import { ClientSideSuspense } from '@liveblocks/react'
import { usePathname } from 'next/navigation'
import { ReactNode } from 'react'
import { RoomProvider } from '@/../liveblocks.config'
import Loading from '@/app/loading'
import { LiveList, LiveObject } from '@liveblocks/client'

type ProjectTool = {
  id: string;
  name : string;
  url? : string;
}

function Room({ children }: { children: ReactNode }) {
  const path = usePathname()

  return (
    <RoomProvider id={path}
                  initialPresence={{}}
                  initialStorage={{
                    outline : new LiveObject({
                      project_name:'', project_description:'',project_startDate:'', project_endDate:'', project_tools:new LiveList<LiveObject<ProjectTool>>()}),
    }}>
      <ClientSideSuspense fallback={<div><Loading/></div>}>
        {() => children}
      </ClientSideSuspense>
    </RoomProvider>
  )
}

export default Room
