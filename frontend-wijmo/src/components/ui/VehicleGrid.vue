<template>
    <div style="max-height:80vh;">
        <div class="hierarchy">
            <div>차량 관리 &nbsp; ></div>
            <div>&nbsp; 차량</div>
        </div>
        <div class="gs-bundle-of-buttons" style="max-height:10vh;">
            <v-btn @click="addNewRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Administrator')">
                <v-icon small>mdi-plus-circle-outline</v-icon>등록
            </v-btn>
            <v-btn  @click="editSelectedRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Administrator')">
                <v-icon small>mdi-pencil</v-icon>수정
            </v-btn>
            <v-btn @click="updateVehicleDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Administrator')">
                <v-icon small>mdi-minus-circle-outline</v-icon>차량 정보 업데이트
            </v-btn>
            <v-dialog v-model="updateVehicleDialog" width="500">
                <UpdateVehicleCommand
                    @closeDialog="updateVehicleDialog = false"
                    @updateVehicle="updateVehicle"
                ></UpdateVehicleCommand>
            </v-dialog>
            <v-btn @click="reportVehicleBreakdownDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Administrator')">
                <v-icon small>mdi-minus-circle-outline</v-icon>차량 고장 신고
            </v-btn>
            <v-dialog v-model="reportVehicleBreakdownDialog" width="500">
                <ReportVehicleBreakdownCommand
                    @closeDialog="reportVehicleBreakdownDialog = false"
                    @reportVehicleBreakdown="reportVehicleBreakdown"
                ></ReportVehicleBreakdownCommand>
            </v-dialog>
            <v-btn @click="deleteSelectedRows" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Administrator')">
                <v-icon small>mdi-minus-circle-outline</v-icon>삭제
            </v-btn>
            <excel-export-button class="contrast-primary-text" :exportService="this.exportService" :getFlex="getFlex" />
        </div>


        <!-- the grid -->
        <wj-flex-grid
            ref="flexGrid"
            :key="value.length"
            :autoGenerateColumns="false"
            :allowAddNew="false"
            :allowDelete="true"
            :allowPinning="'SingleColumn'"
            :newRowAtTop="false"
            :showMarquee="true"
            :selectionMode="'MultiRange'"
            :validateEdits="false"
            :itemsSource="value"
            :initialized="flexInitialized"
            :selectionChanged="onSelectionChanged"
            style="margin-top:10px; max-height:65vh;"
            class="wj-felx-grid"
        >
            <wj-flex-grid-filter :filterColumns="['RowHeader','model','usage','company','registrationNumber','inUse',]" />
            <wj-flex-grid-cell-template cellType="RowHeader" v-slot="cell">{{cell.row.index + 1}}</wj-flex-grid-cell-template>
            <wj-flex-grid-column binding="model" header="모델" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="usage" header="사용 용도" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="company" header="소유회사" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="registrationNumber" header="등록번호" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="inUse" header="사용가능여부" width="2*" :isReadOnly="true" align="center" />
        </wj-flex-grid>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <template>
                    <v-card>
                        <v-toolbar
                            color="primary"
                            class="elevation-0"
                            height="50px"
                        >
                            <div style="color:white; font-size:17px; font-weight:700;">차량 등록</div>
                            <v-spacer></v-spacer>
                            <v-icon
                                color="white"
                                small
                                @click="closeDialog()"
                            >mdi-close</v-icon>
                        </v-toolbar>
                        <v-card-text>
                            <Vehicle :offline="offline"
                                :isNew="!itemToEdit"
                                :editMode="true"
                                v-model="newValue"
                                @add="append"
                                @edit="edit"
                            />
                        </v-card-text>
                    </v-card>
                </template>
            </v-dialog>
        </v-col>
        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                    width="332.5"
                    fullscreen
                    hide-overlay
                    transition="dialog-bottom-transition"
                >
                    <v-btn
                        style="position:absolute; top:2%; right:2%"
                        @click="closeDialog()"
                        depressed
                        icon 
                        absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </div>
</template>

<script>
import BaseGrid from '../base-ui/BaseGrid'

export default {
    name: 'vehicleGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'vehicles',
        updateVehicleDialog: false,
        reportVehicleBreakdownDialog: false,
    }),
    watch: {
    },
    methods:{
        updateVehicle(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "updateVehicle", params)
                this.$EventBus.$emit('show-success','UpdateVehicle 성공적으로 처리되었습니다.')
                this.updateVehicleDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
        reportVehicleBreakdown(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "reportVehicleBreakdown", params)
                this.$EventBus.$emit('show-success','ReportVehicleBreakdown 성공적으로 처리되었습니다.')
                this.reportVehicleBreakdownDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
    }
}
</script>