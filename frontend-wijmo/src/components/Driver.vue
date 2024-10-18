<template>
    <div>
        <String label="이름" v-model="value.name" :editMode="editMode"/>
        <String label="소속" v-model="value.affiliation" :editMode="editMode"/>
        <String label="연락처" v-model="value.contactNumber" :editMode="editMode"/>
        <Boolean label="사용여부" v-model="value.isAvailable" :editMode="editMode"/>

        <v-divider class="border-opacity-100 my-divider"></v-divider>
        <v-layout row justify-end>
            <v-btn
                width="64px"
                color="primary"
                @click="save"
            >
                저장
            </v-btn>
        </v-layout>
    </div>
</template>


<script>

import BaseEntity from './base-ui/BaseEntity'

export default {
    name: 'Driver',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "drivers"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>

